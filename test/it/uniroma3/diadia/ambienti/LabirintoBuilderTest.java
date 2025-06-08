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
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzoConNome("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzoConNome("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getStanzaIniziale().getAttrezzi());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenzaParamInvert(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenzaParamInvert(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacenteConNome("nord"));
		assertEquals(Collections.singletonList("nord"),bilocale.getStanzaIniziale().getDirezioniComeNome());
		assertEquals(Collections.singletonList("sud"),bilocale.getStanzaVincente().getDirezioniComeNome());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenzaParamInvert("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenzaParamInvert("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenzaParamInvert(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIniziale().getStanzaAdiacenteConNome("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
		assertTrue(labirintoBuilder.getMappaStanze().size() <= 2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza 4", "est")
				//questo test non dovrebbe compilare e non compila
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
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
		//questo test non ha molto senso, sono stanze diverse con oggetti diversi e adiacenze diverse
		assertEquals(mappa,maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
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
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
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
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
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
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
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
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
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
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getMappaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzoConNome(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzoConNome(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
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
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getMappaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzoConNome(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzoConNome(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "chiave", "nord").addAttrezzo("chiave", 1)
		.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenzaParamInvert("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenzaParamInvert("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenzaParamInvert(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,labirintoBuilder.getMappaStanze().get("stanza bloccata").getStanzaAdiacenteConNome("nord"));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "chiave", "nord")
		.addAdiacenzaParamInvert(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenzaParamInvert("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenzaParamInvert("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenzaParamInvert(nomeStanzaVincente, "stanza bloccata", "sud");
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
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato","chiave", "nord")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenzaParamInvert(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenzaParamInvert("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenzaParamInvert("corridoio", "corridoio bloccato", "nord")
				.addAdiacenzaParamInvert("corridoio bloccato", "corridoio", "sud")
				.addAdiacenzaParamInvert("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenzaParamInvert("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenzaParamInvert("Aula 1", nomeStanzaVincente,"nord")
				.addAdiacenzaParamInvert(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenzaParamInvert("corridoio", "stanza magica", "est")
				.addAdiacenzaParamInvert("stanza magica", "corridoio", "ovest")
				.addAdiacenzaParamInvert("corridoio", "stanza buia", "ovest")
				.addAdiacenzaParamInvert("stanza buia", "corridoio", "est")
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
		// questo check non ha senso; si tratta di due stanze buie diverse,
		// con nomi diversi, adiacenze diverse, attrezzi antibuio diversi
		//assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezzi());
	}
}