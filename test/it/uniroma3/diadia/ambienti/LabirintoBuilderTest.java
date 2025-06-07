package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaVincenteConNome(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale).addAttrezzoConNome("spada",1)
				.addStanzaVincenteConNome(nomeStanzaIniziale).addAttrezzoConNome("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzoConNome("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzoConNome("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addAttrezzoConNome("spada",1)
				.addAttrezzoConNome("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getStanzaIniziale().getAttrezzi());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaVincenteConNome(nomeStanzaVincente)
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenzaConNomeParamInvert(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacenteConNome("nord"));
		assertEquals(Collections.singletonList("nord"),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList("sud"),bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale).addAttrezzoConNome("sedia", 1)
				.addStanzaConNome("biblioteca")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenzaConNomeParamInvert("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzoConNome("libro antico", 5)
				.addStanzaVincenteConNome(nomeStanzaVincente)
				.addAdiacenzaConNomeParamInvert("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenzaConNomeParamInvert(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIniziale().getStanzaAdiacenteConNome("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaConNome("stanza generica")
				.addStanzaConNome("stanza generica")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
		assertTrue(labirintoBuilder.getMappaStanze().size() <= 2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaConNome("stanza 1")
				.addStanzaConNome("stanza 2")
				.addStanzaConNome("stanza 3")
				.addStanzaConNome("stanza 4")
				.addStanzaConNome("stanza 5")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		assertNull(maze.getStanzaIniziale().getStanzaAdiacenteConNome("nord-est"));
		assertTrue(maze.getStanzaIniziale().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaIniziale().getMapStanzeAdiacenti().containsValue(test));
		Map<String,Stanza> mappa = new HashMap<>();
		mappa.put("nord", new Stanza("stanza 1"));
		mappa.put("ovest", new Stanza("stanza 2"));
		mappa.put("sud", new Stanza("stanza 3"));
		mappa.put("est", new Stanza("stanza 4"));
		assertEquals(mappa,maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaInizialeConNome(this.nomeStanzaIniziale)
				.addStanzaConNome("nuova iniziale")
				.addStanzaInizialeConNome("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanzaInizialeConNome(this.nomeStanzaIniziale)
				.addAttrezzoConNome(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getStanzaIniziale().getAttrezzoConNome(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaConNome(nomeStanza)
				.addAttrezzoConNome(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(this.labirintoBuilder.getMappaStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),this.labirintoBuilder.getMappaStanze().get(nomeStanza).getAttrezzoConNome(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaConNome(nomeStanza)
				.addAttrezzoConNome(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		List<Attrezzo> attrezzi = labirintoBuilder.getMappaStanze().get(nomeStanza).getAttrezzi();
		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
		.addStanzaConNome(nomeStanza1)
		.addAttrezzoConNome(nomeAttrezzo1, peso1)
		.addAttrezzoConNome(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getMappaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzoConNome(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzoConNome(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanzaConNome(nomeStanza1)
		.addStanzaConNome(nomeStanza2)
		.addAttrezzoConNome(nomeAttrezzo1, peso1)
		.addAttrezzoConNome(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getMappaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzoConNome(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzoConNome(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanzaConNome(nomeStanza1)
		.addAttrezzoConNome(nomeAttrezzo1, peso1)
		.addStanzaConNome(nomeStanza2)
		.addAttrezzoConNome(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getMappaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzoConNome(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzoConNome(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagicaConNome(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)labirintoBuilder.getMappaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagicaConNome(nomeStanzaMagica, sogliaMagica)
		.addAttrezzoConNome(nomeAttrezzo1, peso1)
		.addAttrezzoConNome(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getMappaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzoConNome(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzoConNome(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanzaInizialeConNome(nomeStanzaIniziale)
		.addStanzaBloccataConNome("stanza bloccata", "nord", "chiave").addAttrezzoConNome("chiave", 1)
		.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenzaConNomeParamInvert("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincenteConNome(nomeStanzaVincente)
		.addAdiacenzaConNomeParamInvert("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenzaConNomeParamInvert(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,labirintoBuilder.getMappaStanze().get("stanza bloccata").getStanzaAdiacenteConNome("nord"));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanzaInizialeConNome(nomeStanzaIniziale)
		.addStanzaBloccataConNome("stanza bloccata", "chiave", "nord")
		.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenzaConNomeParamInvert("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincenteConNome(nomeStanzaVincente)
		.addAdiacenzaConNomeParamInvert("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenzaConNomeParamInvert(nomeStanzaVincente, "stanza bloccata", "sud");
		//Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "chiave", "nord");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		//test cancellato. questo test non ha senso, la stanzaBloccata locale non fa parte del labirinto
		//assertEquals(stanzaBloccata,labirintoBuilder.getMappaStanze().get("stanza bloccata").getStanzaAdiacenteConNome("nord"));
		Stanza stanzaBloccata2 = labirintoBuilder.getMappaStanze().get("stanza bloccata");
		assertEquals(stanzaBloccata2,stanzaBloccata2.getStanzaAdiacenteConNome("nord"));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.labirintoBuilder
				.addStanzaInizialeConNome(nomeStanzaIniziale)
				.addStanzaVincenteConNome(nomeStanzaVincente)
				.addStanzaConNome("corridoio")
				.addAttrezzoConNome("chiave", 1)
				.addAttrezzoConNome("lanterna", 1)
				.addStanzaBloccataConNome("corridoio bloccato","chiave", "nord")
				.addStanzaMagicaConNome("stanza magica", 1)
				.addStanzaBuiaConNome("stanza buia","lanterna")
				.addStanzaConNome("Aula 1")
				.addAdiacenzaConNomeParamInvert(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenzaConNomeParamInvert("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenzaConNomeParamInvert("corridoio", "corridoio bloccato", "nord")
				.addAdiacenzaConNomeParamInvert("corridoio bloccato", "corridoio", "sud")
				.addAdiacenzaConNomeParamInvert("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenzaConNomeParamInvert("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenzaConNomeParamInvert("Aula 1", nomeStanzaVincente,"nord")
				.addAdiacenzaConNomeParamInvert(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenzaConNomeParamInvert("corridoio", "stanza magica", "est")
				.addAdiacenzaConNomeParamInvert("stanza magica", "corridoio", "ovest")
				.addAdiacenzaConNomeParamInvert("corridoio", "stanza buia", "ovest")
				.addAdiacenzaConNomeParamInvert("stanza buia", "corridoio", "est")
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaIniziale().getStanzaAdiacenteConNome("nord");
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioniComeNome().containsAll(Arrays.asList("ovest","est","nord","sud")));
		Map<String,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put("nord",new Stanza("corridoio bloccato"));
		mapAdiacenti.put("sud",new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put("est",new Stanza("stanza magica"));
		mapAdiacenti.put("ovest",new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezzi());
	}
}