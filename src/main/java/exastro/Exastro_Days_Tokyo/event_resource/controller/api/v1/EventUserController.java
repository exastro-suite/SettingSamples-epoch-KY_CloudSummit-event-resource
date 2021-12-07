/*   Copyright 2021 NEC Corporation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package exastro.Exastro_Days_Tokyo.event_resource.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exastro.Exastro_Days_Tokyo.event_resource.controller.api.v1.form.EventDetailForm;
import exastro.Exastro_Days_Tokyo.event_resource.service.EventUserService;
import exastro.Exastro_Days_Tokyo.event_resource.service.dto.EventDetailDto;

@RestController
@RequestMapping("/api/v1/event")
public class EventUserController extends BaseEventController {
	
	public EventUserController(@Autowired EventUserService service) {
		this.service = service;
	}
	
	@GetMapping("/{eventId}")
	public EventDetailForm eventDetail(@PathVariable(value = "eventId") @Validated int eventId) {
		
		EventDetailForm eventDetail = null;
		try {
			EventDetailDto e = service.getEventDetail(eventId);
			eventDetail = new EventDetailForm(e.getEventId(), e.getEventName(),
					 e.getEventOverview(), e.getEventDate(), e.getEventVenue(), e.getSpeakerIDs());
		
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventDetail;
	}

}
