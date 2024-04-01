package cl.auter.VMSAPI.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Id;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.SequenceModel;
import cl.auter.VMSAPI.model.view.SequenceMessageView;
import cl.auter.VMSAPI.service.SequenceMessageService;
import cl.auter.VMSAPI.service.SequenceMessageViewService;
import cl.auter.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
/*
@RestController
@RequestMapping("/api/sequence")
@SecurityRequirement(name = "JWT")
public class SequenceMessageController {
	
	@Autowired
	private SequenceMessageViewService smService;
	
	@GetMapping("/{id_sequence}/index/{index}")
	public String getJson(@PathVariable("id_sequence") String id_sequence, @PathVariable("index") String index) {
        JSONObject outputJSON = new JSONObject();

        try {        	
            List<SequenceMessageView> sequenceMessages = smService.findAllById(new ArrayList<Integer>(Arrays.asList( Integer.parseInt(id_sequence))));

            for (SequenceMessageView sequenceMessage : sequenceMessages) {
                if (Objects.equals(sequenceMessage.getIndex(), Integer.valueOf(index))) {
                    outputJSON.put("indice", sequenceMessage.getIndex());
                    outputJSON.put("time", sequenceMessage.getTime());
                    outputJSON.put("messageId", sequenceMessage.getMessage_id());
                    outputJSON.put("messageName", sequenceMessage.getMessage_name());
                    outputJSON.put("unit", (sequenceMessage.getCoding() != Constants.ID_DIANMING ? "seg." : "déc. seg." ));
                    break;
                }
            }
        } catch (Exception ex) {       
        	outputJSON.clear();
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    }
	
	@PostMapping("/{id_sequence}/index/{index}")
	public String postJson(@PathVariable("id_sequence") String id_sequence, @PathVariable("index") String index, String content) {
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
	
	@DeleteMapping("/{id_sequence}/index/{index}")
	public String deleteJson(@PathVariable("id_sequence") String id_sequence, @PathVariable("index") String index, String content) {
        JSONObject outputJSON = new JSONObject();
        
        try {
            VMSDAO dao = new VMSDAO();
            SequenceMessageEntity sequenceMessage = new SequenceMessageEntity();
            sequenceMessage.setId(Integer.valueOf(id_sequence));
            sequenceMessage.setIndex(Integer.valueOf(index));
            
            Boolean success = dao.deleteSequenceMessage(sequenceMessage);
            outputJSON.put("deleted", success);
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    }
	
	
	@PostMapping("{id_sequence}/down/{index}")
	public String postJson(@PathVariable("id_sequence") String id_sequence, @PathVariable("index") String index) {
        JSONObject outputJSON = new JSONObject();

        try {
            VMSDAO    dao          = new VMSDAO();
            Integer   indexValue   = Integer.valueOf(index);
            Integer   indexChanged = null;
            SequenceEntity  sequence     = new SequenceEntity();
            sequence.setId(Integer.valueOf(id_sequence));
            
            List<SequenceMessageEntity> sequenceMessages = dao.getSequenceMessages(sequence);
            int                   numMessages      = sequenceMessages.size();
            int                   indexArray       = -1;
            
            // Finds message in list
            for (int i = 0; i < numMessages; i ++) {
                if (Objects.equals(sequenceMessages.get(i).getIndex(), indexValue)) {
                    indexArray = i;
                    break;
                }
            }
            if ((indexArray >= 0) && (indexArray < numMessages - 1)) {
            	SequenceMessageEntity currentMessage = sequenceMessages.get(indexArray);
            	SequenceMessageEntity nextMessage    = sequenceMessages.get(indexArray + 1);
                indexChanged = nextMessage.getIndex();
                
                dao.swapIndexes(currentMessage, nextMessage);
            }

            if (indexValue > 0) {
                outputJSON.put("index", indexChanged);
                outputJSON.put("changed", true);
            } else {
                outputJSON.put("changed", false);
            }
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    }
	
	
	
	@GetMapping("/{id_sequence}")
	public String getJson(@PathVariable("id_sequence") String id_sequence) {
        JSONArray outputJSON = new JSONArray();

        try {
            VMSDAO   dao      = new VMSDAO();
            SequenceEntity sequence = new SequenceEntity();
            sequence.setId(Integer.valueOf(idSequence));
            List<SequenceMessageEntity> sequenceMessages = dao.getSequenceMessages(sequence);

            for (SequenceMessageEntity sequenceMessage : sequenceMessages) {
                JSONObject itemJSON = new JSONObject();

                itemJSON.put("index", sequenceMessage.getIndex());
                itemJSON.put("time", sequenceMessage.getTime());
                itemJSON.put("messageId", sequenceMessage.getMessage().getId());
                itemJSON.put("messageName", sequenceMessage.getMessage().getName());
                itemJSON.put("unit", (sequenceMessage.getMessage().getProtocol() != Constants.ID_DIANMING ? "seg." : "déc. seg." ));

                outputJSON.add(itemJSON);
            }
        } catch (Exception ex) {
            outputJSON.clear();
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("error", ex.toString());
            outputJSON.add(itemJSON);
        }
        
        return outputJSON.toJSONString();
    }
	
	
	@PostMapping("/{id_sequence}")
	public String postJson2(@PathVariable("id_sequence") String id_sequence, String content) {
        JSONParser parser     = new JSONParser();
        JSONObject inputJSON;
        JSONObject outputJSON = new JSONObject();

        try {
            inputJSON = (JSONObject) parser.parse(content);

            Sequence sequence = new Sequence();
            sequence.setId(Integer.valueOf(idSequence));
            sequence.setName((String) inputJSON.get("name"));
            
            try {
                VMSDAO dao = new VMSDAO();
                
                dao.editSignSequence(sequence);
                outputJSON.put("changed", true);
            } catch (Exception ex) {
                outputJSON.put("error", ex.toString());
            }
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    }
	
	@DeleteMapping("/{id_sequence}")
	public String deleteJson(@PathVariable("id_sequence") String id_sequence, String content) {
        JSONObject outputJSON = new JSONObject();
        
        try {
            VMSDAO   dao      = new VMSDAO();
            SequenceEntity sequence = new SequenceEntity();
            
            sequence.setId(Integer.valueOf(id_sequence));
            Boolean success = dao.deleteSignSequence(sequence);
            outputJSON.put("deleted", success);
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        
        return outputJSON.toJSONString();
    
	
}
}*/