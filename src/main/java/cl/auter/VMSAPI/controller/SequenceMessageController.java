package cl.auter.VMSAPI.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.SequenceEntity;
import cl.auter.VMSAPI.model.SequenceMessageEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/sequence")
@SecurityRequirement(name = "JWT")
public class SequenceMessageController {
	
	@GetMapping("/{id_sequence}/index/{index}")
	public String getJson(@PathVariable("id_sequence") String id_sequence, @PathVariable("index") String index) {
        JSONObject outputJSON = new JSONObject();

        try {
            VMSDAO   dao      = new VMSDAO();
            SequenceEntity sequence = new SequenceEntity();
            sequence.setId(Integer.valueOf(id_sequence));
            List<SequenceMessageEntity> sequenceMessages = dao.getSequenceMessages(sequence);

            for (SequenceMessageEntity sequenceMessage : sequenceMessages) {
                if (Objects.equals(sequenceMessage.getIndex(), Integer.valueOf(index))) {
                    outputJSON.put("indice", sequenceMessage.getIndex());
                    outputJSON.put("time", sequenceMessage.getTime());
                    outputJSON.put("messageId", sequenceMessage.getMessage().getId());
                    outputJSON.put("messageName", sequenceMessage.getMessage().getName());
                    outputJSON.put("unit", (sequenceMessage.getMessage().getProtocol() != Constants.ID_DIANMING ? "seg." : "déc. seg." ));
                    break;
                }
            }
        } catch (Exception ex) {            
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    }
	
	@PostMapping("/{id_sequence}/index/{index}")
	public String postJson(@PathVariable("idSequence") String id_sequence, @PathVariable("index") String index, String content) {
        JSONParser parser     = new JSONParser();
        JSONObject inputJSON;
        JSONObject outputJSON = new JSONObject();

        try {
            inputJSON = (JSONObject) parser.parse(content);

            SequenceMessageEntity sequenceMessage = new SequenceMessageEntity();
            sequenceMessage.setId(Integer.valueOf(id_sequence));
            sequenceMessage.setIndex(Integer.valueOf(index));
            sequenceMessage.setTime(((Long) inputJSON.get("time")).intValue());
            
            try {
                VMSDAO dao = new VMSDAO();
                
                dao.editSequenceMessage(sequenceMessage);
                outputJSON.put("changed", true);
            } catch (Exception ex) {
                outputJSON.put("error", ex.toString());
            }
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    }
}
