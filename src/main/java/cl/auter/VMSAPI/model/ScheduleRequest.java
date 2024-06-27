package cl.auter.VMSAPI.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "schedule")
public class ScheduleRequest {
	@Schema(description = "Day of the week (1 -Monday- thru 7 -Sunday-, or 8 -Holiday-). If omitted, gets the system weekday.", example = "1", required = false)
	private String days;
	@Schema(description = "Hour of the specified day, format HH:MM.  If omitted, gets the system hour.", example = "00:00", required = false)
	private String hour;
	@Schema(description = "Ids of the VMS to be checked in a comma-separated list. Ignored for endpoint specifying VMS.", example = "", required = false)
	private String vmsList;
	
	public ScheduleRequest() {
		super();
	}
	
	public ScheduleRequest(String days, String hour, String vmsList) {
		super();
		this.days = days;
		this.hour = hour;
		this.vmsList = vmsList;
	}
	
	public String getDays() {
		return days;
	}
	
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getVmsList() {
		return vmsList;
	}
	
	public void setVmsList(String vmsList) {
		this.vmsList = vmsList;
	}
	
	@Override
	public String toString() {
		return "ScheduleRequest [days=" + days + ", hour=" + hour + ", vmsList=" + vmsList + "]";
	}

	// --------------------------------------------------------------------------------------------
	
	public List<Integer> getVmsIds() {
		List<Integer> vmsIds   = new ArrayList<Integer>();
		String[]      vmsItems = this.vmsList.split(",");
		
		for (String item : vmsItems) {
			try {
				Integer id = Integer.parseInt(item.trim());
				vmsIds.add(id);
			} catch (Exception ex) {
				vmsIds = null;
				break;
			}
		}
		return vmsIds;
	}
	
	public Integer getDayBinary() {
		if (this.days != null) {
			try {
				String[] daysString = this.days.split(",");
				Integer  dayBinary = 0;
				
				for (String dayItem : daysString) {
					Integer day = Integer.parseInt(dayItem.trim());
					if ((day < 1) || (day > 8)) {
						return 0;
					}
					dayBinary |= (1 << (day - 1));
				}
				return dayBinary;
			} catch (Exception ex) {
				return 0;
			}
		} else {
			Calendar cal = Calendar.getInstance();
			
			switch (cal.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.MONDAY :    return 1;
				case Calendar.TUESDAY :   return 2;
				case Calendar.WEDNESDAY : return 4;
				case Calendar.THURSDAY :  return 8;
				case Calendar.FRIDAY :    return 16;
				case Calendar.SATURDAY :  return 32;
				case Calendar.SUNDAY :    return 64;
			}
		}
		return 0;
	}

	public boolean validate() {
		LocalDateTime ldt     = LocalDateTime.now();
		boolean       isValid = (getDayBinary() > 0);
		
		if (isValid) {
			if ((this.hour == null) || (this.hour.isEmpty())) {
				this.hour = ldt.format(DateTimeFormatter.ofPattern("HH:mm"));
			}
			try {
				LocalTime.parse(this.hour, DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT));
			} catch (Exception ex) {
				isValid = false;
			}
		}
		
		return isValid;
	}

}
