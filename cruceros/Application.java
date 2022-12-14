
package es.uca.gii.iw.crusaito;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.uca.gii.iw.crusaito.clases.*;
import es.uca.gii.iw.crusaito.repositorios.*;
import es.uca.gii.iw.crusaito.servicios.*;
/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableJpaRepositories
public class Application extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
			
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Codigo de prueba que guarda usuarios y barcos en la BD
     * @param rolService - rolService define el servicio del rol del usuario.
     * @param userService - userService define el servicio del usuario.
     * @param rolRepository - rolRepository define el repositorio del usuario.
     * @param ciudadService - ciudadService define el servicio de la ciudad.
     * @param cruceroService - cruceroService define el servicio del crucero.
     * @param barcoService - barcoService define el servicio del barco.
     * @param servicioService - servicioService define el servicio de los servicios disponibles.
     * @param servicioUsuarioService - servicioUsuarioService define el servicio de los servicios reservados por el usuario.
     * @param userRepo - userRepo define el repositorio del usuario.
     * @param barcoRepo - barcoRepo define el repositorio del barco.
     * @param cruceroRepo - cruceroRepo define el repositorio del crucero.
     * @return args.
     */
    
    @Bean
    public CommandLineRunner demo(rolService rolService, UsuarioService userService,rolRepository rolRepository, 
    		CiudadService ciudadService, CruceroService cruceroService, BarcoService barcoService,
    		ServicioService servicioService, ServicioUsuarioService servicioUsuarioService,
    		UsuarioRepository userRepo, BarcoRepository barcoRepo, CruceroRepository cruceroRepo) {
        return (args) -> {
        	
        	/**
        	 * Roles de ejemplo
        	 */
        	
        	rolService.save(new Rol("Cliente"));
        	rolService.save(new Rol("Gerente"));
        	rolService.save(new Rol("Admin"));
        	
            /**
             * Usuarios de ejemplo
             */
        	
            userService.save(new Usuario("Jack", "Bauer","cliente@gmail.com","cliente","cliente","12345678Y",
            		123456789,LocalDate.of(1950, 10, 11),"Calle Sagasta","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Ben", "Smith","cliente2@gmail.com","cliente2","cliente2","12345638B",
            		123456789,LocalDate.of(1983, 5, 6),"Calle Tolosa Latour","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Harry", "Smith","cliente3@gmail.com","cliente3","cliente3","12345638B",
            		123456789,LocalDate.of(1980, 3, 10),"Calle Santa Mar??a de la Cabeza","Cadiz",rolRepository.findByName("Cliente")));
            userService.save(new Usuario("Chloe", "O'Brian","admin@gmail.com","admin","admin","12348678A",
            		123456789,LocalDate.of(1979, 9, 7),"Avenida Andaluc??a","Cadiz",rolRepository.findByName("Admin")));
            userService.save(new Usuario("Bill", "Harrinson","gerente@gmail.com","gerente","gerente","12365678G",
            		123456789,LocalDate.of(1964, 1, 4),"Calle Brasil","Cadiz",rolRepository.findByName("Gerente")));
          
            /**
             * Generamos ciudades de ejemplo
             */
            
            /**
             * Ciudades Caribe
             */
            
            Ciudad cadiz = new Ciudad("Cadiz", "De C??diz, el mar, su gente, su bah??a, su historia y sus ganas de vivir.\n" + 
            		"\n" + 
            		"La provincia de C??diz es muy diversa, con comarcas que merecen ser visitadas, desde la campi??a de Jerez de la Frontera a sus pueblos del Campo de Gibraltar o un recorrido por los pueblos blancos y descansar en alg??n lugar de la costa entre Tarifa hasta Sanl??car de Barrameda.\n" + 
            		"\n" + 
            		"Todo ello sin olvidar su capital, que guarda en sus murallas la cultura y las tradiciones que la hacen distinta, como a sus gentes.\n" + 
            		"\n" + 
            		"No lo dudes, C??diz es el destino ideal para tus vacaciones.");
            Ciudad sanFernando = new Ciudad("San Fernando", "Rodeada por el Parque Natural Bah??a de C??diz, "
            		+ "la antigua Isla de Le??n, que toma su nombre actual tras la Guerra "
            		+ "de Independencia (al serle concedido por su valor en la defensa "
            		+ "contra los franceses), ofrece un interesante paisaje de marismas, "
            		+ "salinas y dunas. La presencia de fenicios y romanos queda "
            		+ "atestiguada por los restos conservados en su Museo Hist??rico. "
            		+ "El Castillo de San Romualdo (un ribat isl??mico) defend??a el ??nico "
            		+ "paso terrestre desde el continente hasta C??diz.");
            Ciudad chiclana = new Ciudad("Chiclana", "Fundada en el a??o 1303 por Alfonso P??rez de Guzm??n, en el s.XVIII experimentar?? un "
            		+ "gran desarrollo econ??mico contagi??ndose as?? de la intensa actividad comercial y mercantil de toda la Bah??a. Se construyen "
            		+ "en esta ??poca casas se??oriales como la del Conde del Pinar. La industria vin??cola que ha sustentado tradicionalmente la econom??a "
            		+ "de la poblaci??n (con excelentes vinos que pertenecen a las denominaciones de origen de Jerez-Xeres- Sherry y Manzanilla de Sanl??car) ha ido paulatinamente dejando "
            		+ "paso al sector tur??stico con un auge espectacular en los ??ltimos a??os.");
            Ciudad colon = new Ciudad("Colon", "La provincia de Col??n cubre un tramo casi virgen de la ribera caribe??a en el norte de Panam??. "
            		+ "Las localidades costeras de la provincia fueron importantes puertos comerciales para Espa??a durante la ??poca colonial, "
            		+ "y las reliquias de esta ??poca siguen siendo atracciones tur??sticas para los "
            		+ "visitantes interesados ??????en la historia. Col??n igualmente atrae a los apasionados "
            		+ "de la naturaleza con sus vastas extensiones de selvas v??rgenes, domicilio de "
            		+ "monos aulladores y cientos de g??neros de aves. Las playas c??lidas y las islas "
            		+ "a lo largo de la costa del Caribe completan el atractivo de esta parte de Panam??.");
            
            /**
             * Ciudades Mediterr??neo
             */
            
            Ciudad barcelona = new Ciudad("Barcelona", "Barcelona posee una "
            		+ "impresionante oferta cultural, contando con algunos interesantes museos "
            		+ "como la Fundaci??n Joan Mir?? o el Museo Picasso, aunque donde se aprecia "
            		+ "realmente el arte que envuelve la ciudad es paseando por sus calles "
            		+ "cargadas de encanto.\n" + 
            		"\n" + 
            		"La conocid??sima Sagrada Familia o el sorprendente Parque G??ell son algunas "
            		+ "de las marcas con las que Antonio Gaud?? decor?? la ciudad de un modo "
            		+ "espectacular.\n" + 
            		"\n" + 
            		"Pero Barcelona no es s??lo arte, sino que tambi??n posee soleadas playas "
            		+ "combinadas con una sugerente oferta gastron??mica.");
            Ciudad napoles = new Ciudad("Napoles", "Capital de la regi??n Campania posee m??s de un mill??n de habitantes siendo la tercera ciudad m??s grande de Italia. Linda con el Golfo de N??poles y como vistas tiene el colosal volcan Vesubio.\n" + 
            		"\n" + 
            		"N??poles hay que vivirla: hay que estar dentro de ella para saber "
            		+ "c??mo es: vivaz, desorganizada, con imagen ca??tica, pero dentro de "
            		+ "ese caos mantiene un cierto orden, ya que sus propios habitantes se "
            		+ "crean sus propias reglas: s??lo hay que conducir por las calles de N??poles "
            		+ "para comprender lo que digo..."
            		+ "aunque al principio te crea confusi??n, poco a poco te habit??as.");
            Ciudad roma = new Ciudad("Roma", "Con una larga e interesante historia a sus espaldas, Roma es una ciudad que atrae visitantes de todo el "
            		+ "mundo gracias a sus impresionantes monumentos y restos "
            		+ "arqueol??gicos procedentes de la Antig??edad. Existen infinidad de razones "
            		+ "para visitar Roma, enamorarse de la ciudad y desear volver a ella. La gastronom??a"
            		+ " y su animado ambiente son algunas de ellas.\n" + 
            		"\n" + 
            		"Pasear por Roma no es solo recorrer una antigua ciudad repleta de restos arqueol??gicos; Roma es el recuerdo de los "
            		+ "Gladiadores luchando a vida o muerte en el Coliseo, las cuadrigas emprendiendo veloces carreras en el Circo M??ximo, y tambi??n la visi??n de los sabios romanos paseando por el foro mientras conversaban sobre la democracia.");
            Ciudad marsella = new Ciudad("Marsella", "Marseille es ciudad m??s antigua de Francia, ya que fue fundada por los "
            		+ "foceos en el a??o 600 a. C. Despu??s de que los romanos dejaron su huella, "
            		+ "la ciudad sigui?? desarroll??ndose mediante una verdadera mezcolanza de culturas, "
            		+ "alrededor del puerto Viejo, protegido por sus dos fuertes, Saint-Nicolas y "
            		+ "Saint-Jean. El Puerto Viejo es el centro neur??lgico de la ciudad, y constituye "
            		+ "una etapa ineludible para impregnarse del ambiente del mercado de pescado y de "
            		+ "sus animados muelles, sobre todo en las noches de partido "
            		+ "de f??tbol del equipo de la ciudad, el Olympique de Marsella.");
            
            /**
             * Ciudades Islas Griegas
             */
            
            Ciudad pireo = new Ciudad("El Pireo", "??Qui??n dijo que el puerto del "
            		+ "Pireo es un lugar sin encanto y sin nada interesante a descubrir en ??l? "
            		+ "Lejos del alboroto y el bullicio de los embarcaderos, el mayor puerto de "
            		+ "Grecia es un destino cargado de historia lleno de sitios y monumentos "
            		+ "encantadores, los m??s importantes de los cuales procuraremos  descubrir a "
            		+ "continuaci??n sin pretensi??n alguna, por supuesto, de agotarlos todos. El Pireo dispone de tres puertos menores: Kantharos (el puerto central) de "
            		+ "donde salen los ferries, Zea y Mikrol??mano. Situado a 12 km al suroeste del centro de Atenas, el Pireo es f??cilmente "
            		+ "accesible en autob??s o metro. La estaci??n del metro ha sido recientemente "
            		+ "reformada por lo que se podr??a aprovechar "
            		+ "de este medio para poder visitarla.");
            Ciudad mikonos = new Ciudad("Mikonos", "La isla de M??konos se encuentra en el centro "
            		+ "del archipi??lago de las C??cladas, entre las islas de Tinos, Delos, Siros y Naxos. "
            		+ "M??konos, cuyo nombre se escribe de diversa maneras (Mykonos, M??conos) es una "
            		+ "isla peque??a, con menos de 90 km 2, pero mundialmente conocida gracias a sus "
            		+ "playas y su diversi??n. Las discotecas, junto a la playa y la m??sica electr??nica, "
            		+ "son sin??nimos del verano y del invierno en M??konos, no en vano se ha llamado a "
            		+ "Mykonos la Ibiza de Grecia. Junto a la tambi??n peque??a Santorini, a la gran isla "
            		+ "Creta y a Atenas, Mykonos es una de las escalas m??s visitadas por los "
            		+ "turistas que se acercan a Grecia y al mar Egeo en un viaje en crucero.");
            Ciudad marmaris = new Ciudad("Marmaris", "La local??dad destaca por la abundanc??a de calas en su larga costa, "
            		+ "su puerto natural, su cercan??a a ant??guas c??udades, oportun??dad de real??zar el ???v??aje azul???, sus modernas mar??nas y "
            		+ "ocas????n de la pr??ct??ca de deportes acu??t??cos en su golfo. "
            		+ "La reg????n uno de los pr??nc??pales dest??nos tur??st??cos de Turqu??a, "
            		+ "t??ene numerosos lugares para v??s??tar. En Marmar s la poblac????n se "
            		+ "??ncrementa en los meses de verano con la llegada de tur??stas tanto nac??onales "
            		+ "como extranjeros y la conv??erten en un gran centro tur??st??co, ex??sten calas "
            		+ "tranqu??las y limp??as que se puede acceder a ellas tanto en barcos como por "
            		+ "carretera, adem??s de la "
            		+ "pos??b??l??dad de ba??arse en las playas s??tuadas en la m??sma c??udad.");
            Ciudad creta = new Ciudad("Creta", "Creta, la isla m??s grande de Grecia, es famosa por su diverso terreno, "
            		+ "que va desde las playas de arena fina en Elafonisi hasta las Monta??as Blancas. "
            		+ "En el monte Ida, el m??s alto de la cadena, se encuentra la cueva de Ideon, "
            		+ "donde naci?? Zeus, seg??n la mitolog??a griega. La capital, Heracli??n, "
            		+ "cuenta con el famoso Museo Arqueol??gico de Heracli??n, con artefactos minoicos,"
            		+ " y Cnosos, un asentamiento de la Edad de Bronce.");
            
            /**
             * Ciudades B??ltico
             */
            
            Ciudad tallin = new Ciudad("Tallin", "Tallin, la capital de Estonia sobre el mar B??ltico, es el centro "
            		+ "cultural del pa??s. Conserva su Ciudad Antigua amurallada y con adoquines, "
            		+ "que alberga caf??s y tiendas, junto con Kiek in de K??k, una torre defensiva "
            		+ "del siglo XV. El edificio g??tico del Ayuntamiento, construido en el siglo XIII y con una torre de 64 m de altura, "
            		+ "se encuentra en la hist??rica plaza principal de Tallin.");
            Ciudad rostock = new Ciudad("Rostock", "Rostock es una ciudad alemana, situada en el norte del pa??s, que es famosa por su puerto mar??timo, al que llegan gran cantidad de cruceros tur??sticos. "
            		+ "Con apenas doscientos mil habitantes y localizada en el estado de "
            		+ "Mecklemburgo-Pomerania Occidental, de donde procede Angela Merkel, "
            		+ "a orillas del mar B??ltico, es conocida como la ciudad de la concordia y "
            		+ "la felicidad p??blica por el buen ambiente y el alto nivel de satisfacci??n "
            		+ "que proporciona a sus nativos y turistas.");
            Ciudad helsinki = new Ciudad("Helsinki", "Helsinki es una ciudad que se asoma al mar B??ltico y posee un litoral sinuoso hecho "
            		+ "de bah??as, playas e islas como Suomenlinna y Pihlajasaari, "
            		+ "a las que se llega r??pidamente en ferry. Quiz??s algunos viajeros esperan "
            		+ "una ciudad con un casco antiguo similar a los de Europa del sur y "
            		+ "una arquitectura centenaria, pero la capital de Finlandia posee una historia corta, "
            		+ "es quiz??s la m??s joven de las capitales de los pa??ses n??rdicos. "
            		+ "Aunque est?? claramente marcada por una vanguardia y una tradici??n"
            		+ " en dise??o destacable. Su arquitectura Art Nouveau y funcionalista dibuja "
            		+ "una ciudad at??pica. Es una ciudad de talla humana que invita al visitante "
            		+ "a descubrirla a ritmo lento, par??ndose en sus barrios de dise??o, arquitectura, "
            		+ "compras, cultura, restaurantes, saunas???");
            Ciudad petersburgo = new Ciudad("Petersburgo", "San Petersburgo es una ciudad "
            		+ "considerada como uno de los m??s grandes centros econ??micos, "
            		+ "culturales y cient??ficos de Rusia, Europa y el mundo entero. "
            		+ "Los majestuosos conjuntos arquitect??nicos, palacios magn??ficos, "
            		+ "parques espl??ndidos y museos ??nicos llaman la atenci??n y atraen a los "
            		+ "turistas de todas partes del mundo para sentir el ambiente de la Venecia "
            		+ "del Norte. La ciudad de San Pedro fue fundada por el emperador Pedro el "
            		+ "Grande en 1703 con la intenci??n de convertirla en la \"ventana de Rusia hacia "
            		+ "el mundo occidental\". La ciudad es una combinaci??n de culturas y "
            		+ "tradiciones de Rusia y Europa. ");
           
            /**
             * Ciudades Adri??tico
             */
            
            Ciudad dubrovnik = new Ciudad("Dubrovnik", "En Dubrovnik no solo encontraremos preciosas playas de piedra"
            		+ " con un encanto inusual, si no tambi??n una oferta cultural para aquellos que "
            		+ "quieran disfrutar de un lugar plagado de elegancia, edificios que nos trasladaran "
            		+ "a una ??poca en la que el lujo y la ostentaci??n eran los protagonistas.");
            Ciudad split = new Ciudad("Split", " Nos trasladamos a la regi??n de Dalmacia, "
            		+ "m??s concretamente a Split, el centro neur??lgico de la costa d??lmata,"
            		+ " una ciudad con unos 200,000 habitantes, y sin duda otro lugar clave en nuestro itinerario." + "\n" + "En la historia de Split no podemos pasar por alto algunos datos que dan car??cter a este "
            				+ "lugar, as?? que es de ley saber que el centro antiguo de esta ciudad est?? "
            				+ "unida al Imperio Romano, fue fundada en el S. IV, ya que el emperador Diocleciano,"
            				+ " natural de esta zona, hizo construir su palacio para habitarlo una vez se "
            				+ "retir?? de la vida pol??tica, hoy en d??a es uno de los palacios romanos que mejor "
            				+ "se conservan.");
            Ciudad venecia = new Ciudad("Venecia", "Venecia es un conjunto de 120 islas unidas a trav??s de puentes. "
            		+ "Desde Mestre se puede llegar a Venecia utilizando el Puente de la Libertad, "
            		+ "que lleva hasta la Piazzale Roma.\n" + 
            		"\n" + 
            		"Como es de imaginar, la ciudad ha sufrido inundaciones peri??dicas desde sus "
            		+ "inicios y, a d??a de hoy, la ciudad contin??a sufriendo las amenazas de las "
            		+ "repetidas inundaciones que provocan el fen??meno conocido como Acqua Alta.\n" + 
            		"\n" + 
            		"En primavera y oto??o suele subir el nivel del agua, por lo que es frecuente "
            		+ "que la Plaza de San Marcos se inunde hasta tal punto que las autoridades "
            		+ "tienen que colocar pasarelas para que caminen los peatones.");
            Ciudad zadar = new Ciudad("Zadar", "Este es uno de los mejores lugares para "
            		+ "disfrutar de unas vacaciones activas. Los paisajes de esta regi??n geogr??fica, "
            		+ "desde lo alto de sus monta??as hasta los valles y las islas, ofrecen buenas "
            		+ "oportunidades para disfrutar de vacaciones activas;"
            		+ " aprovechando numerosas veredas al borde del mar y rutas "
            		+ "para ciclistas de monta??a, practicando senderismo, parapente o espeleolog??a." +"\n" + "Es la ciudad con el casco hist??rico m??s grande de Dalmacia. "
            				+ "La ciudad destaca por su paseo mar??timo que ofrece al visitante una visi??n "
            				+ "inolvidable de las islas y de sus puestas de sol acompa??adas del sonido del "
            				+ "?????rgano de Mar??? y del monumento arquitect??nico ??Oda al sol??. Toda una experiencia. "
            				+ "Ambas del arquitecto Nikola Basic. "
            				+ "Adem??s, destaca el Museo del Oro y de la Plata y el Museo de Cristal Antiguo.");
           
            /**
             * Ciudades Fiordos Noruegos
             */
            
            Ciudad bergen = new Ciudad("Bergen", "A nivel de Noruega, Bergen es una ciudad grande, pero ofrece el ambiente y "
            		+ "encanto de una ciudad peque??a. Sus habitantes apasionadamente patri??ticos "
            		+ "est??n orgullosos de las m??ltiples facetas, la historia y las tradiciones "
            		+ "culturales de su ciudad.  Muchos lugare??os est??n encantados de dirigir a los "
            		+ "visitantes a su atracci??n tur??stica, cafeter??a o restaurante favoritos.");
            Ciudad molde = new Ciudad("Molde", "Molde es una ciudad y municipio costero del de la provincia de M??re og Romsdal, Noruega."
            		+ " Debido a la accidentada topograf??a de la zona, el acceso terrestre con el "
            		+ "resto del pa??s es complicado, dependiendo en buena medida del transporte mar??timo."
            		+ " Cuenta con una poblaci??n de 26 392 habitantes seg??n el censo de 2015.1???\n" + 
            		"\n" + 
            		"El municipio est?? situado en la pen??nsula de Romsdal, rodeado por islas y por "
            		+ "los fiordos Fannefjord y Moldefjord. La ciudad est?? situada en la orilla norte "
            		+ "del Romsdalsfjord.");
            Ciudad alesund = new Ciudad("Alesund", "A nivel de Noruega, Bergen es una ciudad grande, pero ofrece el ambiente y encanto de una ciudad peque??a."
            		+ " Sus habitantes apasionadamente patri??ticos est??n orgullosos de las m??ltiples "
            		+ "facetas, la historia y las tradiciones culturales de su ciudad.  "
            		+ "Muchos lugare??os est??n encantados de dirigir a los visitantes a su atracci??n "
            		+ "tur??stica, cafeter??a o restaurante favoritos.");
            Ciudad trondheim = new Ciudad("Trondheim", "Con una poblaci??n de 193.000 habitantes, "
            		+ "Trondheim no es una gran ciudad en t??rminos europeos. "
            		+ "Sin embargo, es la tercera ciudad m??s grande de Noruega. "
            		+ "La amplia variedad de actividades que puedes hacer aqu?? se debe en parte a "
            		+ "los estudiantes, que son m??s de 30.000 en esta ciudad. Los estudiantes dejan "
            		+ "su huella porque organizan muchos eventos, adem??s de asistir a otras ofertas "
            		+ "culturales de la ciudad.\n" + 
            		"\n" + 
            		"Trondheim tiene un n??mero de lugares de inter??s tur??stico que atraen anualmente"
            		+ " a una parte importante de los visitantes de Tr??ndelag. La catedral de"
            		+ " Nidarosdomen ofrece unas vistas espectaculares. La catedral es el santuario "
            		+ "nacional de Noruega, construido sobre la tumba de San Olav. Las obras "
            		+ "comenzaron en 1070, pero los restos m??s antiguos que a??n existen datan de "
            		+ "mediados del siglo XII.");
            
            ciudadService.save(cadiz); ciudadService.save(sanFernando); ciudadService.save(chiclana);
            ciudadService.save(bergen); ciudadService.save(split); ciudadService.save(creta);
            ciudadService.save(molde); ciudadService.save(alesund); ciudadService.save(zadar);
            ciudadService.save(barcelona); ciudadService.save(venecia); ciudadService.save(dubrovnik);
            ciudadService.save(helsinki); ciudadService.save(pireo); ciudadService.save(petersburgo);
            ciudadService.save(trondheim); ciudadService.save(rostock); ciudadService.save(tallin);
            ciudadService.save(colon); ciudadService.save(napoles); ciudadService.save(roma);
            ciudadService.save(marsella); ciudadService.save(mikonos); ciudadService.save(marmaris);
        
            
            /**
             * Barcos de ejemplo
             */
            
         
            Barco antia =barcoRepo.save(new Barco("Antia","/img/crucero1.jpg",1520,150,3200,LocalDate.now(),"Este barco, que fue remodelado en el a??o 2012, ofrece la posibilidad de viajar a un total "
            		+ "de 1520 pasajeros. Cuenta con 9 cubiertas, entre las que se distribuyen agradables"
            		+ " zonas de restauraci??n, con restaurantes variados, buffets, bares y caf??s. "
            		+ "Su oferta de ocio se completa con un gran teatro y un completo casino. "
            		+ "Para el descanso y el deporte tambi??n dispone de spa, piscinas, centro "
            		+ "de belleza y gimnasio, pudiendo completar cada jornada con compras en sus tiendas duty free.", "/img/plano1.jpg"));
            Barco titanic =barcoRepo.save(new Barco("Titanic","/img/titanic.jpg",1000,100,2150,LocalDate.now(),"Con una capacidad total para 1000 pasajeros, este barco es el m??s grande "
            		+ "de los tres que navegan con Crusaito. Cuenta con todo tipo de "
            		+ "instalaciones, repartidas a lo largo de 12 cubiertas, entre las que destacan "
            		+ "restaurantes buffet y a la carta para disfrutar de una agradable velada, bares, "
            		+ "un completo y moderno casino, sal??n de espect??culos, sal??n de juegos, gimnasio, "
            		+ "spa y un amplio sal??n de belleza.", "/img/plano2.jpg"));
            Barco neptuno = barcoRepo.save(new Barco("Neptuno","/img/neptuno.jpg",1530,150,3400,LocalDate.now(),"Este barco con capacidad para 1530 pasajeros, "
            		+ "fue remodelado en el a??o 2014. Cuenta con 12 cubiertas, as?? como multitud de "
            		+ "instalaciones y ofertas de ocio para disfrutar de una diversi??n plena a bordo: grandes salones, "
            		+ "bares, restaurantes, casino y zona de juegos, teatro para la representaci??n de espect??culos, "
            		+ "solarium, biblioteca, discoteca, roc??dromo, pista de p??del, spa, gimnasio y sal??n de belleza.", "/img/plano3.jpg"));
            Barco poseidon =barcoRepo.save(new Barco("Poseid??n","/img/poseidon.jpg",1600,140,2010,LocalDate.now(),"Con una capacidad total para 1600 pasajeros, "
            		+ "este barco es el m??s grande de los tres que navegan con Crusaito. "
            		+ "Cuenta con todo tipo de instalaciones, repartidas a lo largo de 17 cubiertas, "
            		+ "entre las que destacan restaurantes buffet y a la carta para disfrutar de una agradable velada, "
            		+ "bares, un completo y moderno casino, sal??n de espect??culos, sal??n de juegos, gimnasio, "
            		+ "spa y un amplio sal??n de belleza.", "/img/plano4.jpg"));
            Barco siren =barcoRepo.save(new Barco("Siren","/img/siren.jpg",1700,170,3100,LocalDate.now(),"Este barco, que fue remodelado en el a??o 2010, "
            		+ "ofrece la posibilidad de viajar a un total de 1700 pasajeros. "
            		+ "Cuenta con 10 cubiertas, entre las que se distribuyen agradables zonas de "
            		+ "restauraci??n, con restaurantes variados, buffets, bares y caf??s. "
            		+ "Su oferta de ocio se completa con un gran teatro y un completo casino. "
            		+ "Para el descanso y el deporte tambi??n dispone de spa, piscinas, centro de belleza y gimnasio, "
            		+ "pudiendo completar cada jornada con compras en sus tiendas duty free.", "/img/plano5.jpg"));
            Barco spirit =barcoRepo.save(new Barco("Spirit","/img/spirit.jpg",1440,150,3200,LocalDate.now(),"Este barco con capacidad para 1440 pasajeros, "
            		+ "fue remodelado en el a??o 2009. Cuenta con 13 cubiertas, as?? como multitud de "
            		+ "instalaciones y ofertas de ocio para disfrutar de una diversi??n plena a bordo: "
            		+ "grandes salones, bares, restaurantes, casino y zona de juegos, teatro para la "
            		+ "representaci??n de espect??culos, solarium, biblioteca, discoteca, roc??dromo, "
            		+ "pista de p??del, spa, gimnasio y sal??n de belleza.", "/img/plano6.jpg"));
            
            /**
             * Cruceros ejemplo
             */
            
            cruceroService.save(new Crucero("Caribe","C??diz","C??diz","5 d??as","Crucero por el Caribe",300));
            cruceroService.save(new Crucero("Mediterr??neo","Barcelona","Barcelona","12 d??as","Disfruta de las maravillas que te ofrecen los cruceros por el Mediterr??neo de Crusaito\n" + 
            		"Tres continentes, decenas de ciudades y miles de rincones por visitar. Eso es lo que te espera si te embarcas en alguno de los evocadores cruceros por el Mediterr??neo de Crusaito.\n" + 
            		"\n" + 
            		"Este mar, que anta??o fue habitado por muchos de los pueblos y civilizaciones m??s importantes de la historia como los griegos, los romanos o los fenicios, te est?? esperando para que disfrutes de inolvidables experiencias.\n" + 
            		"\n" + 
            		"Disfruta de esta aventura ba??ada por aguas cristalinas y del clima mediterr??neo "
            		+ "durante todo el a??o. ??Todo lo que hab??as imaginado lo podr??s vivir haciendo un crucero por el Mediterr??neo! "
            		+ "Adem??s, Crusaito te ofrece la posibilidad de elegir un crucero por el Mediterr??neo con todo incluido para que s??lo tengas "
            		+ "que estar pendiente de disfrutar y vivir una experiencia ??nica.", 446));
            cruceroService.save(new Crucero("Islas Griegas","El Pireo","El Pireo","10 d??as", "Sum??rgete en el maravilloso mundo de las Islas Griegas "
            		+ "con Crusaito y vive instantes de pel??cula que quedar??n inmortalizados en tu "
            		+ "memoria de la mejor manera, aquella que mentalmente te haga volver a sus apasionantes destinos "
            		+ "en forma de sonrisa.", 1200));
            cruceroService.save(new Crucero("B??ltico","Helsinki","Helsinki","9 d??as","Conoce la riqueza y belleza cultural de las ciudades m??s famosas "
            		+ "y emblem??ticas que viven a orillas del B??ltico, "
            		+ "un mar lleno de historia donde se encuentran las mayores reservas de ??mbar "
            		+ "del mundo y que comunica con el mar del Norte y el oc??ano Atl??ntico.", 2150));
            cruceroService.save(new Crucero("Adri??tico","Dubrovnik","Dubrovnik","8 d??as", "A bordo de tu crucero Crusaito, tendr??s la oportunidad de disfrutar del "
            		+ "incre??ble color azul turquesa de las aguas del mar Adri??tico y un clima "
            		+ "inmejorable que har?? que te plantees quedarte a vivir en sus costas para "
            		+ "toda la vida.", 3400));
            cruceroService.save(new Crucero("Fiordos noruegos","Trondheim","Trondheim","8 d??as", "Viajar a otro mundo sin salir de Europa es completamente posible. "
            		+ "Si te aventuras a embarcar en alguno de nuestros cruceros por los impresionantes "
            		+ "Fiordos Noruegos, ser??s testigo de primera mano de las espectaculares monta??as "
            		+ "que los rodean y disfrutar??s de las singulares excursiones que cada ciudad te "
            		+ "ofrece.", 3400));
           
            
            /**
             * Relaciones
             */
            
            Crucero caribe = cruceroService.findBycNombre("Caribe");
            Crucero mediterraneo = cruceroService.findBycNombre("Mediterr??neo");
            Crucero griego = cruceroService.findBycNombre("Islas Griegas");
            Crucero baltico = cruceroService.findBycNombre("B??ltico");
            Crucero adriatico = cruceroService.findBycNombre("Adri??tico");
            Crucero noruego = cruceroService.findBycNombre("Fiordos noruegos");
            
            /**
             * Ciudades con crucero
             */
            
            /**
             * Caribe
             */
            
            CiudadCrucero ciudadCrucero1 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero2 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero3 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero4 = new CiudadCrucero();
            
            ciudadCrucero1.setCiudad(cadiz);
            ciudadCrucero2.setCiudad(sanFernando);
            ciudadCrucero3.setCiudad(chiclana);
            ciudadCrucero4.setCiudad(colon);
         
            ciudadCrucero1.setFechaLlegada(LocalDate.now());
            ciudadCrucero1.setFechaSalida(LocalDate.now());
            ciudadCrucero1.setHoraLlegada(11);
            ciudadCrucero1.setHoraSalida(15);
            
            ciudadCrucero2.setFechaLlegada(LocalDate.now());
            ciudadCrucero2.setFechaSalida(LocalDate.now());
            ciudadCrucero2.setHoraLlegada(13);
            ciudadCrucero2.setHoraSalida(14);
            
            ciudadCrucero3.setFechaLlegada(LocalDate.now());
            ciudadCrucero3.setFechaSalida(LocalDate.now());
            ciudadCrucero3.setHoraLlegada(15);
            ciudadCrucero3.setHoraSalida(17);
            
            ciudadCrucero4.setFechaLlegada(LocalDate.now());
            ciudadCrucero4.setFechaSalida(LocalDate.now());
            ciudadCrucero4.setHoraLlegada(14);
            ciudadCrucero4.setHoraSalida(16);
            
            ciudadCrucero1.setCrucero(caribe);
            ciudadCrucero2.setCrucero(caribe);
            ciudadCrucero3.setCrucero(caribe);
            ciudadCrucero4.setCrucero(caribe);
            
            cadiz.getCiudadesCruceros().add(ciudadCrucero1);
            sanFernando.getCiudadesCruceros().add(ciudadCrucero2);
            chiclana.getCiudadesCruceros().add(ciudadCrucero3);
            colon.getCiudadesCruceros().add(ciudadCrucero4);
            
            caribe.getCrucerosCiudades().add(ciudadCrucero1);
            caribe.getCrucerosCiudades().add(ciudadCrucero2);
            caribe.getCrucerosCiudades().add(ciudadCrucero3);
            caribe.getCrucerosCiudades().add(ciudadCrucero4);
            
            ciudadService.save(cadiz);
            ciudadService.save(sanFernando);
            ciudadService.save(chiclana);
            ciudadService.save(colon);
            
            cruceroService.save(caribe);
            
            /**
             * Mediterr??neo
             */
            
            CiudadCrucero ciudadCrucero5 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero6 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero7 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero8 = new CiudadCrucero();
            
            ciudadCrucero5.setCiudad(barcelona);
            ciudadCrucero6.setCiudad(napoles);
            ciudadCrucero7.setCiudad(roma);
            ciudadCrucero8.setCiudad(marsella);
         
            ciudadCrucero5.setFechaLlegada(LocalDate.now());
            ciudadCrucero5.setFechaSalida(LocalDate.now());
            ciudadCrucero5.setHoraLlegada(10);
            ciudadCrucero5.setHoraSalida(16);
            
            ciudadCrucero6.setFechaLlegada(LocalDate.now());
            ciudadCrucero6.setFechaSalida(LocalDate.now());
            ciudadCrucero6.setHoraLlegada(13);
            ciudadCrucero6.setHoraSalida(14);
            
            ciudadCrucero7.setFechaLlegada(LocalDate.now());
            ciudadCrucero7.setFechaSalida(LocalDate.now());
            ciudadCrucero7.setHoraLlegada(15);
            ciudadCrucero7.setHoraSalida(19);
            
            ciudadCrucero8.setFechaLlegada(LocalDate.now());
            ciudadCrucero8.setFechaSalida(LocalDate.now());
            ciudadCrucero8.setHoraLlegada(14);
            ciudadCrucero8.setHoraSalida(20);
            
            ciudadCrucero5.setCrucero(mediterraneo);
            ciudadCrucero6.setCrucero(mediterraneo);
            ciudadCrucero7.setCrucero(mediterraneo);
            ciudadCrucero8.setCrucero(mediterraneo);
            
            barcelona.getCiudadesCruceros().add(ciudadCrucero5);
            napoles.getCiudadesCruceros().add(ciudadCrucero6);
            roma.getCiudadesCruceros().add(ciudadCrucero7);
            marsella.getCiudadesCruceros().add(ciudadCrucero8);
            
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero5);
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero6);
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero7);
            mediterraneo.getCrucerosCiudades().add(ciudadCrucero8);
            
            ciudadService.save(barcelona);
            ciudadService.save(napoles);
            ciudadService.save(roma);
            ciudadService.save(marsella);
            
            cruceroService.save(mediterraneo);
            
            /**
             * Islas Griegas
             */
            
            CiudadCrucero ciudadCrucero9 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero10 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero11 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero12 = new CiudadCrucero();
            
            ciudadCrucero9.setCiudad(pireo);
            ciudadCrucero10.setCiudad(mikonos);
            ciudadCrucero11.setCiudad(marmaris);
            ciudadCrucero12.setCiudad(creta);
         
            ciudadCrucero9.setFechaLlegada(LocalDate.now());
            ciudadCrucero9.setFechaSalida(LocalDate.now());
            ciudadCrucero9.setHoraLlegada(7);
            ciudadCrucero9.setHoraSalida(12);
            
            ciudadCrucero10.setFechaLlegada(LocalDate.now());
            ciudadCrucero10.setFechaSalida(LocalDate.now());
            ciudadCrucero10.setHoraLlegada(9);
            ciudadCrucero10.setHoraSalida(14);
            
            ciudadCrucero11.setFechaLlegada(LocalDate.now());
            ciudadCrucero11.setFechaSalida(LocalDate.now());
            ciudadCrucero11.setHoraLlegada(12);
            ciudadCrucero11.setHoraSalida(17);
            
            ciudadCrucero12.setFechaLlegada(LocalDate.now());
            ciudadCrucero12.setFechaSalida(LocalDate.now());
            ciudadCrucero12.setHoraLlegada(13);
            ciudadCrucero12.setHoraSalida(16);
            
            ciudadCrucero9.setCrucero(griego);
            ciudadCrucero10.setCrucero(griego);
            ciudadCrucero11.setCrucero(griego);
            ciudadCrucero12.setCrucero(griego);
            
            pireo.getCiudadesCruceros().add(ciudadCrucero9);
            mikonos.getCiudadesCruceros().add(ciudadCrucero10);
            marmaris.getCiudadesCruceros().add(ciudadCrucero11);
            creta.getCiudadesCruceros().add(ciudadCrucero12);
            
            griego.getCrucerosCiudades().add(ciudadCrucero9);
            griego.getCrucerosCiudades().add(ciudadCrucero10);
            griego.getCrucerosCiudades().add(ciudadCrucero11);
            griego.getCrucerosCiudades().add(ciudadCrucero12);
            
            ciudadService.save(pireo);
            ciudadService.save(mikonos);
            ciudadService.save(marmaris);
            ciudadService.save(creta);
            
            cruceroService.save(griego);
            
            /**
             * B??ltico
             */
     
            CiudadCrucero ciudadCrucero13 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero14 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero15 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero16 = new CiudadCrucero();
            
            ciudadCrucero13.setCiudad(tallin);
            ciudadCrucero14.setCiudad(rostock);
            ciudadCrucero15.setCiudad(helsinki);
            ciudadCrucero16.setCiudad(petersburgo);
         
            ciudadCrucero13.setFechaLlegada(LocalDate.now());
            ciudadCrucero13.setFechaSalida(LocalDate.now());
            ciudadCrucero13.setHoraLlegada(11);
            ciudadCrucero13.setHoraSalida(12);
            
            ciudadCrucero14.setFechaLlegada(LocalDate.now());
            ciudadCrucero14.setFechaSalida(LocalDate.now());
            ciudadCrucero14.setHoraLlegada(13);
            ciudadCrucero14.setHoraSalida(14);
            
            ciudadCrucero15.setFechaLlegada(LocalDate.now());
            ciudadCrucero15.setFechaSalida(LocalDate.now());
            ciudadCrucero15.setHoraLlegada(15);
            ciudadCrucero15.setHoraSalida(17);
            
            ciudadCrucero16.setFechaLlegada(LocalDate.now());
            ciudadCrucero16.setFechaSalida(LocalDate.now());
            ciudadCrucero16.setHoraLlegada(14);
            ciudadCrucero16.setHoraSalida(16);
            
            ciudadCrucero13.setCrucero(baltico);
            ciudadCrucero14.setCrucero(baltico);
            ciudadCrucero15.setCrucero(baltico);
            ciudadCrucero16.setCrucero(baltico);
            
            tallin.getCiudadesCruceros().add(ciudadCrucero13);
            rostock.getCiudadesCruceros().add(ciudadCrucero14);
            helsinki.getCiudadesCruceros().add(ciudadCrucero15);
            petersburgo.getCiudadesCruceros().add(ciudadCrucero16);
            
            baltico.getCrucerosCiudades().add(ciudadCrucero13);
            baltico.getCrucerosCiudades().add(ciudadCrucero14);
            baltico.getCrucerosCiudades().add(ciudadCrucero15);
            baltico.getCrucerosCiudades().add(ciudadCrucero16);
            
            ciudadService.save(tallin);
            ciudadService.save(rostock);
            ciudadService.save(helsinki);
            ciudadService.save(petersburgo);
            
            cruceroService.save(baltico);
            
            /**
             * Adri??tico
             */
            
            CiudadCrucero ciudadCrucero17 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero18 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero19 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero20 = new CiudadCrucero();
            
            ciudadCrucero17.setCiudad(dubrovnik);
            ciudadCrucero18.setCiudad(split);
            ciudadCrucero19.setCiudad(venecia);
            ciudadCrucero20.setCiudad(zadar);
         
            ciudadCrucero17.setFechaLlegada(LocalDate.now());
            ciudadCrucero17.setFechaSalida(LocalDate.now());
            ciudadCrucero17.setHoraLlegada(4);
            ciudadCrucero17.setHoraSalida(12);
            
            ciudadCrucero18.setFechaLlegada(LocalDate.now());
            ciudadCrucero18.setFechaSalida(LocalDate.now());
            ciudadCrucero18.setHoraLlegada(5);
            ciudadCrucero18.setHoraSalida(14);
            
            ciudadCrucero19.setFechaLlegada(LocalDate.now());
            ciudadCrucero19.setFechaSalida(LocalDate.now());
            ciudadCrucero19.setHoraLlegada(6);
            ciudadCrucero19.setHoraSalida(21);
            
            ciudadCrucero20.setFechaLlegada(LocalDate.now());
            ciudadCrucero20.setFechaSalida(LocalDate.now());
            ciudadCrucero20.setHoraLlegada(18);
            ciudadCrucero20.setHoraSalida(19);
            
            ciudadCrucero17.setCrucero(adriatico);
            ciudadCrucero18.setCrucero(adriatico);
            ciudadCrucero19.setCrucero(adriatico);
            ciudadCrucero20.setCrucero(adriatico);
            
            dubrovnik.getCiudadesCruceros().add(ciudadCrucero17);
            split.getCiudadesCruceros().add(ciudadCrucero18);
            venecia.getCiudadesCruceros().add(ciudadCrucero19);
            zadar.getCiudadesCruceros().add(ciudadCrucero20);
            
            adriatico.getCrucerosCiudades().add(ciudadCrucero17);
            adriatico.getCrucerosCiudades().add(ciudadCrucero18);
            adriatico.getCrucerosCiudades().add(ciudadCrucero19);
            adriatico.getCrucerosCiudades().add(ciudadCrucero20);
            
            ciudadService.save(dubrovnik);
            ciudadService.save(split);
            ciudadService.save(venecia);
            ciudadService.save(zadar);
            
            cruceroService.save(adriatico);
            
            /**
             * Fiordos Noruegos
             */
            
            CiudadCrucero ciudadCrucero21 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero22 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero23 = new CiudadCrucero();
            CiudadCrucero ciudadCrucero24 = new CiudadCrucero();
            
            ciudadCrucero21.setCiudad(bergen);
            ciudadCrucero22.setCiudad(molde);
            ciudadCrucero23.setCiudad(alesund);
            ciudadCrucero24.setCiudad(trondheim);
         
            ciudadCrucero21.setFechaLlegada(LocalDate.now());
            ciudadCrucero21.setFechaSalida(LocalDate.now());
            ciudadCrucero21.setHoraLlegada(10);
            ciudadCrucero21.setHoraSalida(17);
            
            ciudadCrucero22.setFechaLlegada(LocalDate.now());
            ciudadCrucero22.setFechaSalida(LocalDate.now());
            ciudadCrucero22.setHoraLlegada(10);
            ciudadCrucero22.setHoraSalida(14);
            
            ciudadCrucero23.setFechaLlegada(LocalDate.now());
            ciudadCrucero23.setFechaSalida(LocalDate.now());
            ciudadCrucero23.setHoraLlegada(11);
            ciudadCrucero23.setHoraSalida(17);
            
            ciudadCrucero24.setFechaLlegada(LocalDate.now());
            ciudadCrucero24.setFechaSalida(LocalDate.now());
            ciudadCrucero24.setHoraLlegada(12);
            ciudadCrucero24.setHoraSalida(22);
            
            ciudadCrucero21.setCrucero(noruego);
            ciudadCrucero22.setCrucero(noruego);
            ciudadCrucero23.setCrucero(noruego);
            ciudadCrucero24.setCrucero(noruego);
            
            bergen.getCiudadesCruceros().add(ciudadCrucero21);
            molde.getCiudadesCruceros().add(ciudadCrucero22);
            alesund.getCiudadesCruceros().add(ciudadCrucero23);
            trondheim.getCiudadesCruceros().add(ciudadCrucero24);
            
            noruego.getCrucerosCiudades().add(ciudadCrucero21);
            noruego.getCrucerosCiudades().add(ciudadCrucero22);
            noruego.getCrucerosCiudades().add(ciudadCrucero23);
            noruego.getCrucerosCiudades().add(ciudadCrucero24);
            
            ciudadService.save(bergen);
            ciudadService.save(molde);
            ciudadService.save(alesund);
            ciudadService.save(trondheim);
            
            cruceroService.save(noruego);
            
            /**
             * Barco con crucero
             */
            
            caribe.setBarco(spirit);
            mediterraneo.setBarco(antia);
            griego.setBarco(titanic);
            baltico.setBarco(neptuno);
            adriatico.setBarco(poseidon);
            noruego.setBarco(siren);
            
            
            /**
             * Usuario 1 con crucero en el caribe
             */
            
            Usuario usuarioEjemplo = userService.findByUsername("cliente");
            usuarioEjemplo.setCrucero(caribe);
            
            userRepo.save(usuarioEjemplo);
            barcoService.save(spirit);
            cruceroService.save(caribe);
            ciudadService.save(cadiz);
            ciudadService.save(sanFernando);
            ciudadService.save(chiclana);
            ciudadService.save(colon);
            
            /**
             * Usuario2 con crucero en el adriatico
             */
            
            Usuario usuarioEjemplo2 = userService.findByUsername("cliente2");
            usuarioEjemplo2.setCrucero(adriatico);
            
            userRepo.save(usuarioEjemplo2);
            barcoService.save(poseidon);
            cruceroService.save(adriatico);
            ciudadService.save(dubrovnik);
            ciudadService.save(split);
            ciudadService.save(venecia);
            ciudadService.save(zadar);
            
            /**
             * Usuario2 con crucero en el baltico
             */
            
            Usuario usuarioEjemplo3 = userService.findByUsername("cliente3");
            usuarioEjemplo3.setCrucero(baltico);
            
            userRepo.save(usuarioEjemplo3);
            barcoService.save(neptuno);
            cruceroService.save(baltico);
            ciudadService.save(tallin);
            ciudadService.save(rostock);
            ciudadService.save(helsinki);
            ciudadService.save(petersburgo);
            
            /**
             * Definici??n de los servicios
             */
            
            Servicio elFaro = new Servicio("El faro", "Mariscadas a lo grande", 30, ServicioTipo.Restaurante, "/img/restaurante.jpg", 0,70, LocalDate.now() );
            Servicio buffet = new Servicio("Buffet libre", "Acceso a todo tipo de comidas", 7, ServicioTipo.Restaurante, "/img/Buffet.jpg", 0,50, LocalDate.now() );
            Servicio wok = new Servicio("Wok menu", "Disfruta de la pasi??n argentina", 15, ServicioTipo.Restaurante, "/img/wok.jpg", 0,65, LocalDate.now() );
            Servicio luxury = new Servicio("Luxury", "Para los paladares m??s exquisitos", 100, ServicioTipo.Restaurante, "/img/Luxury.jpg", 0,50, LocalDate.now() );
            
            Servicio excursion1 = new Servicio("Visita al faro", "Visita guiada al faro. Comienzo de la excursi??n: 12:00", 40, ServicioTipo.Excursion, 2,"/img/islasgriegas.jpg", 30, LocalDate.now());
            Servicio excursion2 = new Servicio("Visita al Museo Municipal", "Visita guiada al Museo Municipal. Comienzo de la excursi??n: 15:30", 20, ServicioTipo.Excursion, 1,"/img/museo.jpg", 30, LocalDate.now());
            Servicio excursion3 = new Servicio("Visita al templo griego", "Visita guiada al templo griego. Comienzo de la excursi??n: 9:00", 30, ServicioTipo.Excursion, 0,"/img/templo.jpg", 30, LocalDate.now());
            Servicio excursion4 = new Servicio("Visita a la Iglesia de Santa Mar??a", "Visita guiada a la Iglesia de Santa Mar??a. Comienzo de la excursi??n: 12:30", 55, ServicioTipo.Excursion, 0,"/img/iglesia.jpg", 30, LocalDate.now());
            Servicio excursion5 = new Servicio("Visita El Florian", "Visita guiada a El Florian. Comienzo de la excursi??n: 17:00", 40, ServicioTipo.Excursion, 3,"/img/cafe.jpg", 30, LocalDate.now());
            Servicio excursion6 = new Servicio("Visita Sagrada Familia", "Visita guiada al La Sagrada Familia. Comienzo de la excursi??n: 13:00", 40, ServicioTipo.Excursion, 0,"/img/sagrada.jpg", 30, LocalDate.now());
            
            excursion1.setCiudad(cadiz);
            cadiz.getServicios().add(excursion1);
            servicioService.save(excursion1);
            
            excursion2.setCiudad(helsinki);
            helsinki.getServicios().add(excursion2);
            servicioService.save(excursion2);
            
            excursion3.setCiudad(pireo);
            pireo.getServicios().add(excursion3);
            servicioService.save(excursion3);
            
            excursion4.setCiudad(bergen);
            bergen.getServicios().add(excursion4);
            servicioService.save(excursion4);
            
            excursion5.setCiudad(venecia);
            venecia.getServicios().add(excursion5);
            servicioService.save(excursion5);
            
            excursion6.setCiudad(barcelona);
            barcelona.getServicios().add(excursion6);
            servicioService.save(excursion6);
            
            
            Servicio spa = new Servicio("Spa fit", "Disfruta de todas las comodidades", 60, ServicioTipo.Otro, "/img/spa.jpg", 0,25, LocalDate.now() );
            Servicio golf = new Servicio("Golf&Cut", "Practica el golf en medio del oc??ano", 40, ServicioTipo.Otro, "/img/golf.jpg", 0,35, LocalDate.now() );
            Servicio padel = new Servicio("Pista padel", "Practica el padel en medio del oc??ano", 20, ServicioTipo.Otro, "/img/padel.jpg", 0,40, LocalDate.now() );
            Servicio ping = new Servicio("Ping-pong", "Practica ping-pong en medio del oc??ano", 15, ServicioTipo.Otro, "/img/ping-pong.jpg", 1,30, LocalDate.now() );
            
            Servicio regalo = new Servicio("Tienda de regalos", "Compra regalos y recuerdos a tus seres queridos", 0, ServicioTipo.Tienda, "/img/regalos.jpg", 0,20, LocalDate.now() );
            Servicio temptempie = new Servicio("Carnival cherry", "Dulces que no encontrar??s en otras partes", 0, ServicioTipo.Tienda, "/img/temptempie.jpg", 0,25, LocalDate.now() );
            Servicio profumeria = new Servicio("La Profumer??a", "Descubre aromas que te sorprender??n", 0, ServicioTipo.Tienda, "/img/profumeria.jpg", 0,25, LocalDate.now() );
            Servicio joyeria = new Servicio("Joyer??a cash", "Experto en piedras preciosas", 0, ServicioTipo.Tienda, "/img/joyeria.jpg", 0,30, LocalDate.now() );
         
            
            servicioService.save(elFaro);
            servicioService.save(buffet);
            servicioService.save(wok);
            servicioService.save(luxury);
    
            servicioService.save(excursion1);
            servicioService.save(excursion2);
            servicioService.save(excursion3);
            servicioService.save(excursion4);
            servicioService.save(excursion5);
            servicioService.save(excursion6);
            
            servicioService.save(spa);
            servicioService.save(golf);
            servicioService.save(padel);
            servicioService.save(ping);
            
            servicioService.save(regalo);
            servicioService.save(temptempie);
            servicioService.save(profumeria);
            servicioService.save(joyeria);
            
            /**
             * Usuario 1 con servicio
             */
         
            ServicioUsuario servUsu = new ServicioUsuario();
            servUsu.setServicio(excursion1);
            servUsu.setUsuario(usuarioEjemplo);
            servUsu.setParticipantes(2);
            servUsu.setPrecio(60);
            
            excursion1.getServiciosUsuarios().add(servUsu);
            usuarioEjemplo.getUsuariosServicios().add(servUsu);
            servicioService.save(excursion1);
            userService.save(usuarioEjemplo);
            servicioUsuarioService.save(servUsu);
            
            /**
             * Usuario 2 con servicio
             */
         
            ServicioUsuario servUsu2 = new ServicioUsuario();
            servUsu2.setServicio(excursion5);
            servUsu2.setUsuario(usuarioEjemplo2);
            servUsu2.setParticipantes(3);
            servUsu2.setPrecio(120);
            
            excursion5.getServiciosUsuarios().add(servUsu2);
            usuarioEjemplo2.getUsuariosServicios().add(servUsu2);
            servicioService.save(excursion5);
            userService.save(usuarioEjemplo2);
            
            /**
             * Usuario 3 con servicio
             */
         
            ServicioUsuario servUsu3 = new ServicioUsuario();
            servUsu3.setServicio(ping);
            servUsu3.setUsuario(usuarioEjemplo3);
            servUsu3.setParticipantes(1);
            servUsu3.setPrecio(15);
            
            ServicioUsuario servUsu4 = new ServicioUsuario();
            servUsu4.setServicio(excursion2);
            servUsu4.setUsuario(usuarioEjemplo3);
            servUsu4.setParticipantes(1);
            servUsu4.setPrecio(20);
            
            ping.getServiciosUsuarios().add(servUsu3);
            usuarioEjemplo3.getUsuariosServicios().add(servUsu3);
            servicioService.save(ping);
            userService.save(usuarioEjemplo3);
            
            excursion2.getServiciosUsuarios().add(servUsu4);
            usuarioEjemplo3.getUsuariosServicios().add(servUsu4);
            servicioService.save(excursion2);
            userService.save(usuarioEjemplo3);
            
            /**
             * Servicio con crucero
             */
            
            caribe.addServicio(excursion1);
            servicioService.save(excursion1);
            caribe.addServicio(golf);
            servicioService.save(golf);
            caribe.addServicio(profumeria);
            servicioService.save(profumeria);
            caribe.addServicio(spa);
            servicioService.save(spa);
            //userService.save(usuarioEjemplo);
            cruceroService.save(caribe);
            
            mediterraneo.addServicio(buffet);
            servicioService.save(buffet);
            mediterraneo.addServicio(golf);
            servicioService.save(golf);
            mediterraneo.addServicio(spa);
            servicioService.save(spa);
            mediterraneo.addServicio(excursion6);
            servicioService.save(excursion6);
            //userService.save(usuarioEjemplo);
            cruceroService.save(mediterraneo);
            
            griego.addServicio(wok);
            servicioService.save(wok);
            griego.addServicio(excursion3);
            servicioService.save(excursion3);
            griego.addServicio(padel);
            servicioService.save(padel);
            griego.addServicio(spa);
            servicioService.save(spa);
            //userService.save(usuarioEjemplo);
            cruceroService.save(griego);
            
            baltico.addServicio(luxury);
            servicioService.save(luxury);
            baltico.addServicio(ping);
            servicioService.save(ping);
            baltico.addServicio(excursion2);
            servicioService.save(excursion2);
            baltico.addServicio(regalo);
            servicioService.save(regalo);
            //userService.save(usuarioEjemplo);
            cruceroService.save(baltico);
            
            adriatico.addServicio(regalo);
            servicioService.save(regalo);
            adriatico.addServicio(golf);
            servicioService.save(golf);
            adriatico.addServicio(profumeria);
            servicioService.save(profumeria);
            adriatico.addServicio(excursion5);
            servicioService.save(excursion5);
            cruceroService.save(adriatico);
            
            noruego.addServicio(elFaro);
            servicioService.save(elFaro);
            noruego.addServicio(golf);
            servicioService.save(golf);
            noruego.addServicio(excursion4);
            servicioService.save(excursion4);
            noruego.addServicio(spa);
            servicioService.save(spa);
            cruceroService.save(noruego);
            
            log.info("Servicios con findByUsuario");
            Usuario usucliente = userService.findByUsername("cliente");
            	log.info(usucliente.getFirstName());
              
        };
    }
}

