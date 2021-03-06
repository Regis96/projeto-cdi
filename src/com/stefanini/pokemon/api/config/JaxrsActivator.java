package com.stefanini.pokemon.api.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.stefanini.pokemon.api.PokemonApi;
import com.stefanini.pokemon.api.TreinadorApi;
import com.stefanini.pokemon.log.Log;

@ApplicationPath("/rest")
public class JaxrsActivator extends Application {

	Set<Class<?>> classes = new HashSet<>();

	public JaxrsActivator() {
		Log.info("Registrando resources...");
		classes.add(PokemonApi.class);
		classes.add(TreinadorApi.class);
		Log.info("Resources registrados com sucesso...");
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
	
}
