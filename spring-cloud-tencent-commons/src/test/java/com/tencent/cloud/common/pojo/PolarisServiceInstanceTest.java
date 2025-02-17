/*
 * Tencent is pleased to support the open source community by making Spring Cloud Tencent available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.tencent.cloud.common.pojo;

import com.tencent.polaris.api.pojo.Instance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.tencent.polaris.test.common.Consts.SERVICE_PROVIDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Test for {@link PolarisServiceInstance}.
 *
 * @author Haotian Zhang
 */
@ExtendWith(MockitoExtension.class)
public class PolarisServiceInstanceTest {

	@Test
	@DisplayName("test getters and setters.")
	void test() {
		Instance secureInstance = mock(Instance.class);
		doReturn("test-ID").when(secureInstance).getId();
		doReturn(SERVICE_PROVIDER).when(secureInstance).getService();
		doReturn("1.1.1.1").when(secureInstance).getHost();
		doReturn(8080).when(secureInstance).getPort();
		doReturn("https").when(secureInstance).getProtocol();

		PolarisServiceInstance securePolarisServiceInstance = new PolarisServiceInstance(secureInstance);
		assertThat(securePolarisServiceInstance.getInstanceId()).isEqualTo("test-ID");
		assertThat(securePolarisServiceInstance.getServiceId()).isEqualTo(SERVICE_PROVIDER);
		assertThat(securePolarisServiceInstance.getHost()).isEqualTo("1.1.1.1");
		assertThat(securePolarisServiceInstance.getPort()).isEqualTo(8080);
		assertThat(securePolarisServiceInstance.isSecure()).isTrue();
		assertThat(securePolarisServiceInstance.getScheme()).isEqualTo("https");

		Instance insecureInstance = mock(Instance.class);
		doReturn("http").when(insecureInstance).getProtocol();
		PolarisServiceInstance insecurePolarisServiceInstance = new PolarisServiceInstance(insecureInstance);
		assertThat(insecurePolarisServiceInstance.isSecure()).isFalse();
		assertThat(insecurePolarisServiceInstance.getScheme()).isEqualTo("http");
	}
}
