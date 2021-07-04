package com.napster.tags;

import com.napster.tags.domain.Tag;
import com.napster.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
public class AccessingDataMongodbApplication{

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMongodbApplication.class, args);
	}

	@Autowired
	private TagRepository tagRepository;

	@PostConstruct
	public void buildIndex() {

		Long tagsCount = tagRepository.count();
		System.out.println("\n\n\n\n\n tagsCount["+tagsCount+"]");
		if(tagsCount > 0 )return ;

		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
		tagRepository.saveAll(prepareTags());
		System.out.println("\n\n\n\n\n new 100,000 Records Inserted");
	}

	private Collection<Tag> prepareTags() {


		List<Tag> tagsList = new ArrayList<Tag>();

		Tag tag = null;
		String[] tags = new String[]{"Favorite","Dislike"};
		String[] cType = new String[]{"track","video","album","playlist","singer"};
		Random ran = new Random();
		try{

			for(int i = 0; i < 100000 ; i++){
				tag = new Tag(UUID.randomUUID().toString(),
						UUID.randomUUID().toString(),
						tags[(i%2)],
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
