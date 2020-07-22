package challenge;

import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	private final QuoteRepository repository;

	public QuoteServiceImpl(QuoteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Quote getQuote() {
		return Util.getRandomElement(repository.findAll());
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		return Util.getRandomElement(repository.findByActor(actor));
	}

}