package cl.auter.VMSAPI.controller;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;

import cl.auter.VMSAPI.model.VmsCredentialsEntity;
import cl.auter.VMSAPI.repository.VmsCredentialsRepository;
import cl.auter.util.DecodeJwt;
import cl.auter.util.JWTResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/video")
public class VideoController {
	
	private final VmsCredentialsRepository vmscRep;
	private       DecodeJwt       decJwt = new DecodeJwt();
	
	@Autowired
	public VideoController(VmsCredentialsRepository vmsc) {
		this.vmscRep = vmsc;
	}
	
    @GetMapping("/{vms_id}")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> livestream(@PathVariable int vms_id) throws Exception {
    	try {
    		//JWTResponse          jwtResponse = decJwt.validateToken(authorizationHeader);
    		VmsCredentialsEntity vmce        = vmscRep.findByVmsId(vms_id);    		
    		
    		String rtspUrl = "rtsp://"
    				+ vmce.getCam_username() + ":"
    				+ vmce.getCam_password() + "@"
    				+ vmce.getIp_address()   + ":"
    				+ vmce.getPort();
    		
	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(os -> {
	                    FFmpeg.atPath()
	                            .addArgument("-re")
	                            .addArguments("-acodec", "pcm_s16le")
	                            .addArguments("-rtsp_transport", "tcp")
	                            .addArguments("-i", rtspUrl)
	                            .addArguments("-vcodec", "copy")
	                            .addArguments("-af", "asetrate=22050")
	                            .addArguments("-acodec", "aac")
	                            .addArguments("-b:a", "96k" )
	                            .addOutput(PipeOutput.pumpTo(os)
	                                    .disableStream(StreamType.AUDIO)
	                                    .disableStream(StreamType.SUBTITLE)
	                                    .disableStream(StreamType.DATA)
	                                    .setFrameCount(StreamType.VIDEO, 10000000000L)
	                                    .setFrameRate(25)
	                                    .setDuration(10, TimeUnit.HOURS)
	                                    .setFormat("ismv"))
	                            .addArgument("-nostdin")
	                            .execute();
	                });
    	}
    	catch(Exception e) {
    		//System.out.println(e);
    		return null;
    	}
    }
}