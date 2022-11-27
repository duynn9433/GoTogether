package duynn.gotogether;

import duynn.gotogether.dto.entity_dto.TransportDTO;
import duynn.gotogether.entity.Transport;
import duynn.gotogether.util.enumClass.TransportType;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoTogetherApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ModelMapper modelMapper;

	@Test
	void test() {
		Transport transport = new Transport();
		transport.setTransportType(TransportType.MOTORCYCLE);
		TransportDTO transportDTO = modelMapper.map(transport, TransportDTO.class);
		System.out.println(transportDTO.getTransportType());


	}

}
