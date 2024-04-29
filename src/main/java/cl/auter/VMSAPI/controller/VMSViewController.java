package cl.auter.VMSAPI.controller;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.BrightnessEntity;
import cl.auter.VMSAPI.model.Cabinet;
import cl.auter.VMSAPI.model.GrupoModel;
import cl.auter.VMSAPI.model.LetreroComModel;
import cl.auter.VMSAPI.model.SequenceViewModel;
import cl.auter.VMSAPI.model.MessageImage;
import cl.auter.VMSAPI.model.MessageModel;
import cl.auter.VMSAPI.model.MessagePreviewModel;
import cl.auter.VMSAPI.model.NewSignModel;
import cl.auter.VMSAPI.model.SequenceMessageModel;
import cl.auter.VMSAPI.model.SideImage;
import cl.auter.VMSAPI.model.SideImageModel;
import cl.auter.VMSAPI.model.SignModel;
import cl.auter.VMSAPI.model.SymbolModel;
import cl.auter.VMSAPI.model.view.MessageViewModel;
import cl.auter.VMSAPI.model.view.SignMessageViewModel;
import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.model.view.VMSViewModel;
import cl.auter.VMSAPI.protocol.DIANMING;
import cl.auter.VMSAPI.protocol.DIANMINGInfo;
import cl.auter.VMSAPI.service.GroupService;
import cl.auter.VMSAPI.service.MessageService;
import cl.auter.VMSAPI.service.MessageViewService;
import cl.auter.VMSAPI.service.SequenceMessageService;
import cl.auter.VMSAPI.service.SequenceService;
import cl.auter.VMSAPI.service.SequenceViewService;
import cl.auter.VMSAPI.service.SideImageService;
import cl.auter.VMSAPI.service.SignComService;
import cl.auter.VMSAPI.service.SignMessageViewService;
import cl.auter.VMSAPI.service.SignService;
import cl.auter.VMSAPI.service.SignTypeViewService;
import cl.auter.VMSAPI.service.SymbolService;
import cl.auter.VMSAPI.service.VMSViewService;
import cl.auter.util.Constants;
import cl.auter.util.VMSUtils;
import cl.auter.VMSAPI.model.VMSResponseEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sign")
public class VMSViewController {
	@Autowired
	VMSViewService vmsService;
	@Autowired
	SignTypeViewService signTypeViewService;
	@Autowired
	SignMessageViewService signMessageViewService;
	@Autowired
	MessageViewService messageViewService;
	@Autowired
	MessageService messageService;
	@Autowired
	SequenceViewService sequenceViewService;
	@Autowired
	SymbolService symbolService;
	@Autowired
	SignService signService;
	@Autowired
	SignComService signComService;
	@Autowired
	GroupService groupService;
	@Autowired
	SideImageService sideImageService;
	@Autowired
	SequenceService sequenceService;
	// JPérez 2024.04.04
	@Autowired
	SequenceMessageService sequenceMessageService;

	@GetMapping("")
	public List<VMSViewModel> findAll() {
		List<VMSViewModel> vmsList = vmsService.findAll();
		/*for (VMSViewModel sign : vmsList) {
			if (sign.getCodificacion() == Constants.ID_DIANMING) { // Diangming
				DIANMING dianming = new DIANMING(sign);
				dianming.setAddresses(sign.getDireccion());
				List<DIANMINGInfo> cabinetsInfo = dianming.getDetailedStatus();
				// socket.close();
				List<Cabinet> cabinets = new ArrayList<Cabinet>();
				int idGabinete = 0;
				for (DIANMINGInfo cabinet : cabinetsInfo) {
					Cabinet cabinetAux = new Cabinet();
					idGabinete++;
					cabinetAux.setCabinetId(idGabinete);
					cabinetAux.setCabinetName("Cabinet" + VMSUtils.ZeroPad(idGabinete, 2));
					cabinetAux.setTemperature(cabinet.getTemperature());
					cabinetAux.setVoltage1(cabinet.getVoltage1());
					cabinetAux.setVoltage2(cabinet.getVoltage2());
					cabinetAux.setDoorSwitch1(cabinet.getDoorSwitch1());
					cabinetAux.setDoorSwitch2(cabinet.getDoorSwitch2());
					cabinetAux.setBrightness(cabinet.getBrightness());
					cabinetAux.setPhotosensitive1(cabinet.getPhotosensitive1());
					cabinetAux.setPhotosensitive2(cabinet.getPhotosensitive2());

					cabinets.add(cabinetAux);
				}
				sign.setCabinets(cabinets);
			}

		}*/
		return vmsList;
	}

	@PostMapping("")
	public VMSResponseEntity newSign(@RequestBody NewSignModel json) {
		System.out.println(json.toString());
		SignModel sign = new SignModel();
		sign.setId_tipo_letrero(json.getSignTypeId());
		sign.setLatitud(json.getLatitude());
		sign.setLongitud(json.getLongitude());
		sign.setNombre(json.getName());
		sign.setObs(json.getObs());
		sign.setUbicacion(json.getLocation());
		System.out.println(sign.toString());
		signService.save(sign);
		signService.flush();
		System.out.println(sign.toString());
		if (sign.getId_letrero() > 0) {
			LetreroComModel letreroCom = new LetreroComModel();
			letreroCom.setId_letrero(sign.getId_letrero());
			letreroCom.setId_conexion(json.getConnectionId());
			letreroCom.setFono(json.getPhoneOrIP());
			letreroCom.setClave(json.getKey());
			letreroCom.setCanal(json.getChannel());
			letreroCom.setDireccion(json.getAddress());
			letreroCom.setPort(json.getPort());
			letreroCom.setId_camara(json.getCameraId());
			signComService.save(letreroCom);
		}

		return null;
	}

	@GetMapping("/{id}")
	public VMSViewModel findById(@PathVariable("id") Integer vms_id) {
		VMSViewModel sign = vmsService.findVMSById(vms_id);
		try {
			if (sign.getCodificacion() == Constants.ID_DIANMING) { // Diangming
				DIANMING dianming = new DIANMING(sign);
				dianming.setAddresses(sign.getDireccion());
				List<DIANMINGInfo> cabinetsInfo = dianming.getDetailedStatus();
				System.out.println("cabinetsInfo " + cabinetsInfo.size());
				// socket.close();
				List<Cabinet> cabinets = new ArrayList<Cabinet>();
				int idGabinete = 1;
				for (DIANMINGInfo cabinet : cabinetsInfo) {
					Cabinet cabinetAux = new Cabinet();

					cabinetAux.setCabinetId(idGabinete);
					cabinetAux.setCabinetName("Cabinet" + VMSUtils.ZeroPad(idGabinete, 2));
					cabinetAux.setTemperature(cabinet.getTemperature());
					cabinetAux.setVoltage1(cabinet.getVoltage1());
					cabinetAux.setVoltage2(cabinet.getVoltage2());
					cabinetAux.setDoorSwitch1(cabinet.getDoorSwitch1());
					cabinetAux.setDoorSwitch2(cabinet.getDoorSwitch2());
					cabinetAux.setBrightness(cabinet.getBrightness());
					cabinetAux.setPhotosensitive1(cabinet.getPhotosensitive1());
					cabinetAux.setPhotosensitive2(cabinet.getPhotosensitive2());
					cabinets.add(cabinetAux);
					idGabinete++;
				}
				sign.setCabinets(cabinets);
			}
		} catch (Exception ex) {
			// outputJSON.clear();
		}
		return sign;
	}

	@GetMapping("/{id}/message")
	public List<SignMessageViewModel> getJson(@PathVariable("id") int idSign) {
		List<SignMessageViewModel> messages = signMessageViewService.findAllBySignId(idSign);
		return messages;
	}

	@GetMapping("/{id}/group")
	public List<GrupoModel> getGroups(@PathVariable("id") int idSign) {
		// VMSViewModel vms = signMessageViewService.getById(idSign);
		// List<GrupoModel> messages = groupService.getById(idSign);
		return new ArrayList<>();
	}

	@GetMapping("/{id}/brightness")
	public BrightnessEntity getBrightness(@PathVariable("id") int idSign) {

		// List<GrupoModel> messages = groupService.findAllById(idSign);
		BrightnessEntity demo = new BrightnessEntity();
		demo.setAutomatic(false);
		demo.setValue(45);
		return demo;
	}

	@PutMapping("/{id}/brightness/{value}")
	public VMSResponseEntity updateBrightness(@PathVariable("id") int idSign,
			@PathVariable("id") String brightnessValue) {

		VMSResponseEntity response = new VMSResponseEntity();
		response.setMessage("");
		response.setStatus(500);
		try {
			if ((brightnessValue.compareToIgnoreCase("FF") == 0)
					|| (brightnessValue.compareToIgnoreCase("AUTO") == 0)) {
				brightnessValue = "255";
			}
			VMSViewModel sign = vmsService.getById(idSign);

			if (sign.getCodificacion() == Constants.ID_DIANMING) {
				Integer brightness = Integer.valueOf(brightnessValue);
				if (((brightness < 0) || (brightness >= 32)) && (brightness != 255)) {
					response.setMessage("Brightness value out of range");
				}

				if (response.getMessage().equals("")) {
					Thread t1 = new Thread(new Runnable() {
						@Override
						public void run() {
							DIANMING dianming = new DIANMING(sign);
							dianming.setBrightness(brightness);
						}
					});
					t1.start();

				}
			} else {
				response.setMessage("VMS is not DIANMING (protocol: " + sign.getProtocol_name() + ")");
			}

		} catch (Exception ex) {
			response.setMessage(ex.toString());
		}

		if (response.getMessage().equals("")) {
			response.setMessage("success");
			response.setStatus(200);
		}
		return response;
	}

	@GetMapping("/{idSign}/sequence")
	public List<SequenceViewModel> getSequence(@PathVariable("idSign") int idSign) {
		List<SequenceViewModel> sequences = sequenceViewService.findByIdVMS(idSign);
		return sequences;
	}

	/**
	 * PUT method for updating or creating an instance of VMSRestSignSequenceList
	 * 
	 * @param idSign  resource URI parameter
	 * @param content representation for the resource
	 */

	@PutMapping("/{idSign}")
	public ResponseEntity<JsonObject> editSign(@PathVariable("idSign") Integer id, @RequestBody VMSViewModel vms) {
		LetreroComModel letreroCom = new LetreroComModel();
		letreroCom.setId_letrero(vms.getId_letrero());
		letreroCom.setId_conexion(vms.getId_conexion());
		letreroCom.setFono(vms.getFono());
		letreroCom.setClave(vms.getClave());
		letreroCom.setCanal(vms.getCanal());
		letreroCom.setDireccion(vms.getDireccion());
		letreroCom.setPort(vms.getPort());
		letreroCom.setId_camara(vms.getId_camara());
		signComService.save(letreroCom);
		SignModel sign = new SignModel();
		sign.setId_letrero(vms.getId_letrero());
		sign.setId_tipo_letrero(vms.getId_tipo_letrero());
		sign.setUbicacion(vms.getUbicacion());
		sign.setLatitud(vms.getLatitud());
		sign.setLongitud(vms.getLongitud());
		sign.setNombre(vms.getNombre());
		sign.setObs(vms.getObs());
		signService.save(sign);
		JsonObject json = Json.createObjectBuilder().add("result", "ok").build();
		return new ResponseEntity<JsonObject>(json, HttpStatus.OK);
	}

	@DeleteMapping("/{idSign}")
	public ResponseEntity<JsonObject> deleteSign(@PathVariable("idSign") Integer idSign) {
		SignModel sign = signService.getById(idSign);
		JsonObjectBuilder job = Json.createObjectBuilder();
		if (sign != null) {
			signService.deleteById(sign.getId_letrero());
			messageService.deleteBySignId(sign.getId_letrero());
			job.add("result", "ok");
			return new ResponseEntity<JsonObject>(job.build(), HttpStatus.OK);
		} else {
			job.add("result", "Id no existe");
			return new ResponseEntity<JsonObject>(job.build(), HttpStatus.NOT_MODIFIED);
		}

	}

	@PostMapping("{sign_id}/message/{message_id}/send")
	public ResponseEntity<JsonObject> sendMessage(@PathVariable int sign_id, @PathVariable int message_id,
			@RequestBody MessageViewModel message) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		VMSViewModel sign = vmsService.getById(sign_id);
		MessageViewModel message0 = messageViewService.getById(message_id);
		try {
			if (sign.getCodificacion() == Constants.ID_DIANMING) {

				if ((message != null) && (message.getMessage() != null)) {
					message0.setMessage(message.getMessage());
				}
				SideImageModel simLeft = sideImageService.getSideImage(message_id, 0);
				SideImageModel simRight = sideImageService.getSideImage(message_id, 1);
				List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(message0.getGroupId(),
						VMSUtils.CharsAsStringList(message0.getMessage()));
				MessageImage mi = new MessageImage(message0);
				mi.setSymbols(symbolsModel, new SideImage(simLeft), new SideImage(simRight));
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						DIANMING dianming = new DIANMING(sign);
						dianming.setAddresses(sign.getDireccion());
						dianming.sendMessage(sign, mi);
						// MessageViewModel message0 = messageViewService.getById(message_id);
					}
				});
				t1.start();
				job.add("result", "success");
				return new ResponseEntity<JsonObject>(job.build(), HttpStatus.OK);
			} else {
				job.add("result",
						"Sending messages to VMS with " + sign.getDireccion() + " protocol is still not supported.");
				return new ResponseEntity<JsonObject>(job.build(), HttpStatus.NOT_MODIFIED);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			job.add("result", "error: " + ex.toString());
			return new ResponseEntity<JsonObject>(job.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("{sign_id}/message/send")
	public ResponseEntity<JsonObject> sendTempMessage(@PathVariable("sign_id") int sign_id,
			@RequestBody MessagePreviewModel json) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		try {
			// Recupera info para generar imagen
			VMSViewModel sign = vmsService.getById(sign_id);
			SignTypeViewModel signType = signTypeViewService.getById(sign.getId_tipo_letrero());
			SideImageModel simLeft = null;
			SideImageModel simRight = null;
			String message = json.getMessage();
			MessageModel messageModel = new MessageModel();

			if (json.getGroupId() != null) {
				messageModel.setGroup_id(json.getGroupId());
			} else {
				messageModel.setGroup_id(7);
			}
			if (json.getColour() != null) {
				messageModel.setFont_color(json.getColour());
			}
			if (json.getSpacing() != null) {
				messageModel.setSpacing(json.getSpacing());
			}
			if (json.getMessage() != null) {
				messageModel.setMessage(json.getMessage());
			}
			if (json.getAlignmentId() != null) {
				messageModel.setAlignmentId(json.getAlignmentId());
			}

			List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(messageModel.getGroup_id(),
					VMSUtils.CharsAsStringList(message));
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

			// Genera imagen
			MessageImage mi = new MessageImage(signType, messageModel.getAlignmentId(), messageModel.getFont_color(),
					messageModel.getSpacing(), messageModel.getMessage());
			mi.setSymbols(symbolsModel, new SideImage(simLeft), new SideImage(simRight));

			// Envío a VMS
			VMSViewModel signModel = new VMSViewModel();
			signModel.setFono(sign.getFono());
			signModel.setPort(sign.getPort());
			if (signType.getProtocolId() == Constants.ID_DIANMING) {
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						DIANMING dianming = new DIANMING(signModel);
						dianming.setAddresses(sign.getDireccion());
						dianming.sendMessage(sign, mi);
					}
				});
				t1.start();

				job.add("result", "ok");
				new ResponseEntity<JsonObject>(job.build(), HttpStatus.OK);
			} else {
				job.add("result", "Sending messages to VMS with " + signModel.getDireccion()
						+ " protocol is still not supported.");
				new ResponseEntity<JsonObject>(job.build(), HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception ex) {
			job.add("result", "error " + ex);
			new ResponseEntity<JsonObject>(job.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		job.add("result", "ok");
		return new ResponseEntity<JsonObject>(job.build(), HttpStatus.OK);
	}

	// JPérez 2024.04.04
	@PostMapping("{sign_id}/sequence/{sequence_id}/send")
	public ResponseEntity<JsonObject> sendSequence(@PathVariable int sign_id, @PathVariable int sequence_id) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		VMSViewModel sign = vmsService.getById(sign_id);
		System.out.println("SIGN " + sign.toString());
		try {
			if (sign.getCodificacion() == Constants.ID_DIANMING) {
				List<SequenceMessageModel> sequenceMessages = sequenceMessageService.findSeqAllById(sequence_id);
				List<MessageImage> images = new ArrayList<MessageImage>();
				List<Integer> times = new ArrayList<Integer>();
				for (SequenceMessageModel sm : sequenceMessages) {
					MessageModel message0 = messageService.getById(sm.getMessage_id());
					SignTypeViewModel stv = signTypeViewService.getById(message0.getType());
					SideImageModel simLeft = sideImageService.getSideImage(message0.getMessage_id(), 0);
					SideImageModel simRight = sideImageService.getSideImage(message0.getMessage_id(), 1);
					List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(message0.getGroup_id(),
							VMSUtils.CharsAsStringList(message0.getMessage()));
					MessageImage mi = new MessageImage(stv, message0.getAlignmentId(), message0.getFont_color(),
							message0.getSpacing(), message0.getMessage());
					mi.setSymbols(symbolsModel, new SideImage(simLeft), new SideImage(simRight));
					images.add(mi);
					times.add(sm.getTime()*10); //decimas a segundos
				}

				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						DIANMING dianming = new DIANMING(sign);
						dianming.setAddresses(sign.getDireccion());
						dianming.sendSequence(images, times);
					}
				});
				t1.start();
				job.add("result", "success");
				return new ResponseEntity<JsonObject>(job.build(), HttpStatus.OK);
			} else {
				job.add("result",
						"Sending messages to VMS with " + sign.getDireccion() + " protocol is still not supported.");
				return new ResponseEntity<JsonObject>(job.build(), HttpStatus.NOT_MODIFIED);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			job.add("result", "error: " + ex.toString());
			return new ResponseEntity<JsonObject>(job.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// JPérez 2024.04.29
	@GetMapping("{sign_id}/turnon/{is_on]")
	public ResponseEntity<JsonObject> turnOn(@PathVariable int sign_id, @PathVariable int is_on) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		VMSViewModel sign = vmsService.getById(sign_id);
		
		if ((is_on != 0) && (is_on != 1)) {
			job.add("result", "Invalid value for turning on/off VMS.");
			return new ResponseEntity<JsonObject>(job.build(), HttpStatus.NOT_MODIFIED);
		}
		try {
			if (sign.getCodificacion() == Constants.ID_DIANMING) {
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						DIANMING dianming = new DIANMING(sign);
						dianming.setAddresses(sign.getDireccion());
						if (is_on == 0) {
							dianming.turnOffVMS();
						} else {
							dianming.turnOnVMS();
						}
					}
				});
				t1.start();
				job.add("result", "success");
				return new ResponseEntity<JsonObject>(job.build(), HttpStatus.OK);
			} else {
				job.add("result", "Protocol not supported for this operation.");
				return new ResponseEntity<JsonObject>(job.build(), HttpStatus.NOT_MODIFIED);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			job.add("result", "error: " + ex.toString());
			return new ResponseEntity<JsonObject>(job.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
