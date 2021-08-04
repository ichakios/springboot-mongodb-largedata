package com.napster.tags;

import com.napster.tags.domain.Tag;
import com.napster.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@RestController
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class AccessingDataMongodbApplication{

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMongodbApplication.class, args);
	}
	@Autowired
	private Environment env;

	@GetMapping("/hello")
	public ResponseEntity<String> getHello(Model model) {
		return new ResponseEntity<String>("abc = "+ env.getProperty("source.database.type"), HttpStatus.OK);
	}

	@Autowired
	private TagRepository tagRepository;

	@PostConstruct
	public void buildIndex() {

		Long tagsCount = tagRepository.count();
		System.out.println("\n\n\n\n\n tagsCount["+tagsCount+"]");
		if(tagsCount > 0 ){
			//tagRepository.saveAll(prepareTags());
			return ;
		}

		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted"+ new Date());
	}

	private Collection<Tag> prepareTags() {


		List<Tag> tagsList = new ArrayList<Tag>();

		Tag tag = null;
		String[] tags = new String[]{"Favorite","Dislike","Next"};
		String[] cType = new String[]{"track","video","album","playlist","singer"};
		Random ran = new Random();
		try{
			UUID uuid = null;
			for(int i = 0; i < 100000 ; i++){

				if(i % 5000 == 0) {
					uuid = UUID.randomUUID();
				}
				tag = new Tag(UUID.randomUUID().toString(),
						uuid.toString(),
						tags[ran.nextInt(3)],
						Calendar.getInstance().getTime(),
						cType[ran.nextInt(5)],
						cType[(i%2)]+ "_" + ran.nextInt(900000000) );
				tagsList.add(tag);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagsList;
	}
}
