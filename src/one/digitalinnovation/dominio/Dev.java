package one.digitalinnovation.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dev {

	private String nome;
	private List<Conteudo> conteudosInscritos = new ArrayList<>();
	private List<Conteudo> conteudosConcluidos = new ArrayList<>();

	public void inscrever(Conteudo conteudo) {
		if (conteudosInscritos.contains(conteudo)) {
			System.err.println("Você já se inscreceu nesse conteúdo.");
		} else {
			conteudosInscritos.add(conteudo);
		}
	}

	public void inscrever(Bootcamp bootcamp) {
		// Alternativa 1: foreach tradicional.
		for (Conteudo conteudo : bootcamp.getConteudos()) {
			inscrever(conteudo);
		}
		bootcamp.getDevs().add(this);
		// Alternativa 2: API de Stream.
		//bootcamp.getConteudos().stream().forEach(this::inscrever);
	}

	public void progredir() {
		Optional<Conteudo> conteudo = conteudosInscritos.stream().findFirst();
		if (conteudo.isPresent()) {
			conteudosConcluidos.add(conteudo.get());
			conteudosInscritos.remove(conteudo.get());
		} else {
			System.err.println("Você não está mais inscrito em nenhum conteúdo.");
		}
	}
	
	public double calcularTotalXp() {
		return conteudosConcluidos.stream()
				.mapToDouble(conteudo -> conteudo.calcularXp())
				.sum();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
