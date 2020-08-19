package com.example.lottery.service.business;

import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnCloudPlatform(CloudPlatform.NONE)
public class UnitCalculator {

}
