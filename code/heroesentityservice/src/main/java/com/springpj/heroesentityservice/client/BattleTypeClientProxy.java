package com.springpj.heroesentityservice.client;


import com.springpj.heroesentityservice.model.battlecapacity.BattleCapacityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "heroes-battletype-service")
public interface BattleTypeClientProxy {
	@PostMapping("/battle-type/capacity/bulkAdd")
	List<BattleCapacityDto> bulkAddCapacities(@RequestBody List<BattleCapacityDto> battleCapacities);

}
