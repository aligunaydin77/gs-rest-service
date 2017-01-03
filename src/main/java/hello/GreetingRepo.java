package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingRepo extends MongoRepository<Greeting, String> {

}
