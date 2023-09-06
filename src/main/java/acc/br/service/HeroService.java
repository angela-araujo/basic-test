package acc.br.service;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

import acc.br.model.Hero;

@ApplicationScoped

public class HeroService {
	ArrayList<Hero> heros = new ArrayList<Hero>( 
			Arrays.asList(new Hero("Bruce","Wayne"), new Hero("Peter","Parker")) );

	public ArrayList<Hero> getHeros() {
		return heros;
	}

	public void add(Hero hero) {
		getHeros().add(hero);
		
	}
}
