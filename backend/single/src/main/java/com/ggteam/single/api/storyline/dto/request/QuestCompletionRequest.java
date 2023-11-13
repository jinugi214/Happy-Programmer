package com.ggteam.single.api.storyline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestCompletionRequest {
    private Long characterId;
    private List<Integer> questIdList;
}
