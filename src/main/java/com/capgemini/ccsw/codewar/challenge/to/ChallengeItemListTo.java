package com.capgemini.ccsw.codewar.challenge.to;

import java.util.Date;

public interface ChallengeItemListTo {

   Long getId();

   String getName();

   String getDescription();

   String getStatusCode();

   String getStatusName();

   Date getStartDate();

   Date getEndDate();

   Boolean getMultipleTries();

   String getTagsName();

   Long getNumParticipation();
}
