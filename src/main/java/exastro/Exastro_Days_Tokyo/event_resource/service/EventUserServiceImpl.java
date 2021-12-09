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

package exastro.Exastro_Days_Tokyo.event_resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exastro.Exastro_Days_Tokyo.event_resource.repository.entity.EventDetail;
import exastro.Exastro_Days_Tokyo.event_resource.service.dto.EventDetailDto;

@Service
public class EventUserServiceImpl extends BaseEventService implements EventUserService {

	@Autowired
	protected SeminarUserService seminarSvc;
	
	public EventUserServiceImpl() {
		
	}

	public EventDetailDto getEventDetail(int eventId) {
		
		EventDetailDto eventInfo =null;
		
		try {
			//イベントID に紐づくイベント情報を取得
			EventDetail ev = repository.findByEventIdIs(eventId);
			eventInfo = new EventDetailDto(ev.getEventId(), ev.getEventName(), 
					ev.getEventOverview(), ev.getEventDate(), ev.getEventVenue());

		
			//該当イベントを含むセミナー一覧から登壇者IDを取得
			List<Integer> speakerIdList = seminarSvc.getSpeakerIdList(eventId);
			
			//登壇者情報をイベント情報内にセット
			eventInfo.setSpeakerIDs(speakerIdList);
		}
		catch(Exception e) {
			throw e;
		}

		return eventInfo;
	}

}
