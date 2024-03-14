package cl.auter.VMSAPI.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.MessageModel;
import cl.auter.VMSAPI.model.MessagePreviewModel;
import cl.auter.VMSAPI.model.SideImage;
import cl.auter.VMSAPI.model.SideImageModel;
import cl.auter.VMSAPI.model.SignModel;
import cl.auter.VMSAPI.model.SideImageMessageEntity;
import cl.auter.VMSAPI.model.SymbolModel;
import cl.auter.VMSAPI.model.VMSResponseEntity;
import cl.auter.VMSAPI.model.view.MessageViewModel;
import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.service.GroupService;
import cl.auter.VMSAPI.service.MessageService;
import cl.auter.VMSAPI.service.MessageViewService;
import cl.auter.VMSAPI.service.SideImageService;
import cl.auter.VMSAPI.service.SignTypeViewService;
import cl.auter.VMSAPI.service.SymbolService;
import cl.auter.util.VMSUtils;
import cl.auter.VMSAPI.model.GrupoModel;
import cl.auter.VMSAPI.model.MensajeModel;
import cl.auter.VMSAPI.model.MessageImage;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/message")
public class MessageController {
	@Autowired
	MessageService messageService;
	@Autowired
	MessageViewService messageViewService;
	@Autowired
	SymbolService symbolService;
	@Autowired
	SideImageService sideImageService;
	@Autowired
	SignTypeViewService signTypeViewService;
	@Autowired
	GroupService groupService;

	@GetMapping("")
	public List<MessageModel> findAll(){
		List<MessageModel> messageList = messageService.findAll();
		return messageList;		
		
	}
	
	@GetMapping("/{id}")
	public MessageModel findById(@PathVariable int id){
		MessageModel message = messageService.getById(id);
		return message;		
		
	}
	
	@PostMapping("/{id}")
	public MessageModel updateMessage(@PathVariable int id, @RequestBody MessagePreviewModel json){
		MessageModel message = messageService.getById(id);
		return message;		
		
	}
	
	@GetMapping("/{id}/group")
	public GrupoModel findGroupByMessageId(@PathVariable int id){
		GrupoModel group = groupService.getById(id);
		return group;		
		
	}
	
	@PostMapping("")
	public VMSResponseEntity createMessage(@RequestBody MensajeModel message ) {
		
		System.out.println(message.toString());
		return null;
		
	}
	
	@GetMapping("/{id}/image")
	public String findImageById(@PathVariable int id){
		JSONObject outputJSON = new JSONObject();
        try {
        	MessageViewModel messageViewModel = messageViewService.getById(id);
        	MessageImage mi  = new MessageImage(messageViewModel);
        	List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(messageViewModel.getGroupId(), VMSUtils.CharsAsStringList(messageViewModel.getMessage()));
        	SideImage leftImage  = new SideImage(sideImageService.getSideImage(messageViewModel.getId(), 0));
            SideImage rightImage = new SideImage(sideImageService.getSideImage(messageViewModel.getId(), 1));
        	mi.setSymbols(symbolsModel, leftImage, rightImage);
        	String b64 = mi.getBase64();
            outputJSON.put("mime", "image/bmp");
            outputJSON.put("data", b64); 
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        return outputJSON.toString();

	}
	
	@PostMapping("/{id}/image")
	public String getMessageImage(@PathVariable("id") int id_message, @RequestBody MessagePreviewModel json) {
		JSONObject outputJSON = new JSONObject();
		try {
			SideImageModel          simLeft  = null;
			SideImageModel          simRight = null;
			String                  message  = json.getMessage();
			MessageModel            messageModel      = messageService.getById(id_message);
			List<SignTypeViewModel> st       = signTypeViewService.findAllBySignTypeId(messageModel.getType());
			
			if ((st == null) || st.isEmpty()) {
				throw new Exception("No sign type defined for the given VMS");
			}
			
			if(json.getGroupId() != null)
				messageModel.setGroup_id(json.getGroupId());
			if(json.getAlignmentId() != null)
				messageModel.setAlignment_id(json.getAlignmentId());
			if(json.getColour() != null)
				messageModel.setFont_color(json.getColour());
			if(json.getSpacing() != null)
				messageModel.setSpacing(json.getSpacing());
			if(json.getMessage() != null)
			{
				messageModel.setMessage(json.getMessage());
			}
			
			
			List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(messageModel.getGroup_id(), VMSUtils.CharsAsStringList(message));
			if ((json.getImageB64_left() != null) && (json.getVerticalAlign_left() != null)) {
				simLeft = new SideImageModel();
				simLeft.setUbicacion_hrz(0);
				simLeft.setUbicacion_vrt(json.getVerticalAlign_left());
				simLeft.setImagen_b64(json.getImageB64_left());
			}
			if ((json.getImageB64_right() != null) && (json.getVerticalAlign_right() != null)) {
				simRight = new SideImageModel();
				simRight.setUbicacion_hrz(1);
				simRight.setUbicacion_vrt(json.getVerticalAlign_right());
				simRight.setImagen_b64(json.getImageB64_right());
			}
			
			MessageImage mi = new MessageImage(st.get(0), messageModel.getAlignment_id(), messageModel.getFont_color(), messageModel.getSpacing(), messageModel.getMessage());
			mi.setSymbols(symbolsModel, new SideImage(simLeft), new SideImage(simRight));
        	String b64 = mi.getBase64();
            outputJSON.put("mime", "image/bmp");
            outputJSON.put("data", b64); 
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        return outputJSON.toString();
	}
	
	
	@GetMapping("/{message_id}/image/{side}")
	public String  findImageLeft(@PathVariable int message_id,@PathVariable int side){
		JSONObject outputJSON = new JSONObject();
        try {
        	MessageViewModel messageViewModel = messageViewService.getById(message_id);
        	SideImageModel imageModel = sideImageService.getSideImage(messageViewModel.getId(),side);
        	SideImage image  = new SideImage(imageModel);
        	String b64 = image.getImageB64();
            outputJSON.put("mime", "image/bmp");
            outputJSON.put("data", b64);
            outputJSON.put("id", imageModel.getId());
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        return outputJSON.toString();
	}

	@PutMapping("/{message_id}/image/{side}")
	public VMSResponseEntity  deleteImageMessage(@PathVariable int message_id,@PathVariable int side,@RequestBody SideImageMessageEntity json){
		VMSResponseEntity response = new VMSResponseEntity();
		if(json.getData() == null || json.getData().equals(""))
		{
			sideImageService.deleteById(json.getId());
			response.setStatus(200);
			response.setMessage("ok");
			return response;
		}
		SideImageModel image  = sideImageService.getSideImage(message_id, side);
		if(image == null)
		{
			image = new SideImageModel();
			
		}
		image.setId_mensaje(message_id);
		image.setId_usuario(json.getUserId());
		image.setUbicacion_hrz(side);
		image.setImagen_b64(json.getData());
		image.setUbicacion_vrt(json.getLocation());
		image.setId(json.getId());
		System.out.println(image.toString());
		sideImageService.save(image);
		
		

		//sideImageService.updateImage(image.getImagen_b64(),image.getUbicacion_vrt(),image.getId_mensaje(),image.getUbicacion_hrz());
		response.setStatus(200);
		response.setMessage("ok");
		return response;	
	
	}
	
	@DeleteMapping("/{message_id}")
	public VMSResponseEntity  deleteMessage(@PathVariable int message_id){
		VMSResponseEntity response = new VMSResponseEntity();
		messageService.deleteById(message_id);
		response.setStatus(200);
		response.setMessage("ok");
		return response;	
	
	}
	
	

}
