package com.ilvo.neo4jdemo;

import java.util.List;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import org.springframework.shell.jline.PromptProvider;

import com.ilvo.neo4jdemo.dto.ActedInProperty;
import com.ilvo.neo4jdemo.dto.ReviewedProperties;
import com.ilvo.neo4jdemo.nodes.Movie;
import com.ilvo.neo4jdemo.nodes.Person;
import com.ilvo.neo4jdemo.repository.MovieRepository;
import com.ilvo.neo4jdemo.repository.PersonRepository;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jdemoApplication {

	private final static Logger log = LoggerFactory.getLogger(Neo4jdemoApplication.class);
	public static void main(String[] args){
		SpringApplication application = new SpringApplication(Neo4jdemoApplication.class);
		application.setBannerMode(Mode.OFF);
		application.run(args);
		// SpringApplication.run(Neo4jdemoApplication.class, args);
	}
    
    @Bean
	public PromptProvider promptProvider() {
		return () -> new AttributedString("shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN));
	}

	@Bean
	CommandLineRunner nodeQueryTests(PersonRepository personRepository, MovieRepository movieRepository) {
		return args -> {
            			
			// 1.查找 Mike Nichols 导演的全部电影
			Person Mike_Nichols = personRepository.findByName("Mike Nichols");
			log.info(Mike_Nichols.infomation());
			
			List<Movie> directedMovies = movieRepository.findDirectedMovies(Mike_Nichols.getName());
			log.info("1. Movies directed by Mike Nichols :");
			directedMovies.forEach(movie->log.info(movie.infomation()));
            
            
			// 2.查找Robin Williams 演出的全部电影
            Person Robin_Williams = personRepository.findByName("Robin Williams");
			log.info(Robin_Williams.infomation());
			
			List<Movie> actedInMovies = movieRepository.findActedInMovies(Robin_Williams.getName());
			log.info("2. Movies acted by Robin Williams :");
			actedInMovies.forEach(movie->log.info(movie.infomation()));
            

			// 3.查找James Thompson评论的全部电影
			Person James_Thompson = personRepository.findByName("James Thompson");
			log.info(James_Thompson.infomation());
			
			List<Movie> reviewedMovies = movieRepository.findReviewedMovies(James_Thompson.getName());
			log.info("3. Movies reviewed by James Thompson :");
			reviewedMovies.forEach(movie->log.info(movie.infomation()));


			// 4.查找Joel Silver制作的全部电影
			Person Joel_Silver = personRepository.findByName("Joel Silver");
			log.info(Joel_Silver.infomation());
			
			List<Movie> producedMovies = movieRepository.findProducedMovies(Joel_Silver.getName());
			log.info("4. Movies produced by Joel Silver :");
			producedMovies.forEach(movie->log.info(movie.infomation()));
            
            
			// 5.查找Lana Wachowski编剧的全部电影
			Person Lana_Wachowski = personRepository.findByName("Lana Wachowski");
			log.info(Lana_Wachowski.infomation());
			
			List<Movie> writtenMovies = movieRepository.findWrittenMovies(Lana_Wachowski.getName());
			log.info("5. Movies written by Lana Wachowski :");
			writtenMovies.forEach(movie->log.info(movie.infomation()));
			

            // 6.查找Jessica Thompson 的所有徒弟(跟随者)
			Person Jessica_Thompson = personRepository.findByName("Jessica Thompson");
			log.info(Jessica_Thompson.infomation());

            List<Person> followers = personRepository.findFollowers(Jessica_Thompson.getName());
			log.info("6. followers of Jessica Thompson :");
			followers.forEach(person->log.info(person.infomation()));
            

			// 7.查找Paul Blythe的全部师傅
			Person Paul_Blythe = personRepository.findByName("Paul Blythe");
			log.info(Paul_Blythe.infomation());
            
			List<Person> masters = personRepository.findMasters(Paul_Blythe.getName());
			log.info("7. Masters followed by Paul Blythe :");
			masters.forEach(person->log.info(person.infomation()));

			
			// 8.查找某电影的所有人员
            Movie The_Green_Mile = movieRepository.findByTitle("The Green Mile");
			log.info(The_Green_Mile.infomation());

			List<Person> allRelativePeople = personRepository.findAllRelativePeopleOfMovie(The_Green_Mile.getTitle());
			log.info("8. all relative people of the movie The Greeb Mile :");
			allRelativePeople.forEach(person->log.info(person.infomation()));
			

		};
	}

	@Bean
	CommandLineRunner nodeTests(PersonRepository personRepository, MovieRepository movieRepository) {
		return args -> {
			//  测试 节点的创建，无属性关系的创建，以及节点与无属性关系的链接
			log.info("测试节点的创建，无属性关系的创建，以及节点与无属性关系的链接");
			Person follower = new Person("follower",2002);
			Person master = new Person("master",1980);
			follower.follows(master);
			personRepository.save(master);
			personRepository.save(follower);
			
			follower = personRepository.findByName(follower.getName());
			log.info("follower的个人信息");
			log.info(follower.infomation());

			List<Person> masters = personRepository.findMasters(follower.getName());
			log.info("Masters :");
				masters.forEach(person->log.info(person.infomation()));
			
		};
	}
    

	@Bean
	CommandLineRunner propertiesOfRelationTests(PersonRepository personRepository, MovieRepository movieRepository) {
		return args -> {
            // 测试 有属性的关系的创建与 关系的属性的查询
			log.info("测试 有属性的关系的创建与 关系的属性的查询");
			Person follower = personRepository.findByName("follower");
			log.info("follower的个人信息");
			log.info(follower.infomation());

			Movie movie1 = new Movie("movie1", "movie1_tagline", 2000);
			movieRepository.save(movie1);

			movie1 = movieRepository.findByTitle(movie1.getTitle());
            log.info("movie1的信息");
			log.info(movie1.infomation());
            
			String[] roles = {"role1","role2"};
            follower.actedIn(movie1, roles);
			follower.reviewed(movie1, "summary1", 23);
			personRepository.save(follower);

			List<Movie> actedInMovies = movieRepository.findActedInMovies(follower.getName());
			log.info("Moives which follower acted in :");
				actedInMovies.forEach(movie->log.info(movie.infomation()));

			ActedInProperty actedInProperty = follower.getActedInProperty(movie1);
			log.info("the roles of ActedIn relationship between follower and movie1 : ");
			roles = actedInProperty.getRoles();
			int length = roles.length;
			for(int i=0;i< length;i++){
				log.info(roles[i]);
			}

            follower.deleteActedIn(movie1);
			personRepository.save(follower); //  also failed,see also 

			
			List<Movie> reviewedMovies = movieRepository.findReviewedMovies(follower.getName());
			log.info("Moives which follower reviewed :");
				reviewedMovies.forEach(movie->log.info(movie.infomation()));
			
			ReviewedProperties reviewedProperties = follower.getReviewedProperties(movie1);
			log.info("the summary of Reviewed relationship between follower and movie1 : ");
			log.info(reviewedProperties.getSummary());
			log.info("the rating of Reviewed relationship between follower and movie1 : ");
			log.info(reviewedProperties.getRating().toString());

		};
	}

	@Bean
	CommandLineRunner propertiesOfRelationInMovieDatasetTests(PersonRepository personRepository, MovieRepository movieRepository){
		return args ->{
			log.info("Movie数据集中的关系的属性查询测试");
			Person Marshall_Bell = personRepository.findByName("Marshall Bell");
			List<Movie> actedInMovies = movieRepository.findActedInMovies(Marshall_Bell.getName());
			log.info("Movies actedIn by Marshall Bell :");

			actedInMovies.forEach(movie -> { 
		
				ActedInProperty actedInProperty = Marshall_Bell.getActedInProperty(movie);
				log.info("the roles of ActedIn relationship between Marshall Bell and movie "+movie.getTitle()+" :");
				String[] roles =  actedInProperty.getRoles();
			    int length = roles.length;
			    for(int i=0;i< length;i++){
				    log.info(roles[i]);
			        }

		        } );

            
			Person Jessica_Thompson = personRepository.findByName("Jessica Thompson");
		    List<Movie> reviewedMovies = movieRepository.findReviewedMovies(Jessica_Thompson.getName());
		    log.info("Moives which Jessica Thompson reviewed :");

			    reviewedMovies.forEach(movie-> {
					
					ReviewedProperties reviewedProperties = Jessica_Thompson.getReviewedProperties(movie);
					log.info("the summary of Reviewed relationship between Jessica Thompson and movie "+movie.getTitle()+" :");
					log.info(reviewedProperties.getSummary());
					log.info("the rating of Reviewed relationship between Jessica Thompson and movie "+movie.getTitle()+" :");
					log.info(reviewedProperties.getRating().toString());


				} );

		   


		};
	}

	@Bean
	CommandLineRunner clean(PersonRepository personRepository, MovieRepository movieRepository){
		return args ->{
			// 这是最终的清除步骤，以免影响Movie数据集
			log.info("清除自定义的数据,以免影响Movie数据集");
			Person follower = personRepository.findByName("follower");
			Person master = personRepository.findByName("master");
			Movie movie1 = movieRepository.findByTitle("movie1");
			personRepository.delete(follower);
			personRepository.delete(master);
			movieRepository.delete(movie1);
			
		};
	}


}
