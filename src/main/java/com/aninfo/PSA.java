package com.aninfo;

import com.aninfo.model.CargaDeHoras;
import com.aninfo.model.Recurso;
import com.aninfo.service.CargaDeHorasService;
import com.aninfo.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class PSA {

	@Autowired
	private CargaDeHorasService cargaDeHorasService;

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(PSA.class, args);
	}

	@PostMapping("/carga")
	@ResponseStatus(HttpStatus.CREATED)
	public CargaDeHoras cargar(@RequestBody CargaDeHoras carga) {
		return cargaDeHorasService.createCargaDeHoras(carga);
	}

	@GetMapping("/carga")
	public Collection<CargaDeHoras> getCargas() {
		return cargaDeHorasService.getCargasDeHoras();
	}

	@GetMapping("/carga/{id}")
	public ResponseEntity<CargaDeHoras> getCarga(@PathVariable Long id) {
		Optional<CargaDeHoras> cargaDeHorasOptional = cargaDeHorasService.findById(id);
		return ResponseEntity.of(cargaDeHorasOptional);
	}

	@DeleteMapping("/carga/{id}")
	public void deleteCarga(@PathVariable Long id) {
		cargaDeHorasService.deleteById(id);
	}

	@PutMapping("/carga/{id}")
	public ResponseEntity<CargaDeHoras> updateCarga(@RequestBody CargaDeHoras carga, @PathVariable Long id) {
		Optional<CargaDeHoras> cargaDeHorasOptional = cargaDeHorasService.findById(id);

		if (!cargaDeHorasOptional.isPresent()){
			return ResponseEntity.notFound().build();
		}

		carga.setIdCarga(id);
		cargaDeHorasService.save(carga);
		return ResponseEntity.noContent().build();
	}


	@Autowired
	private RecursoService recursoService;

	@GetMapping("/recurso")
	public Collection<Recurso> getRecursos() {
		return recursoService.getRecurso();
	}

	@GetMapping("/recurso/{legajo}")
	public ResponseEntity<Recurso> getRecurso( @PathVariable long legajo) {
		Optional<Recurso> recurso =  recursoService.getRecursoByLegajo(legajo);
		return ResponseEntity.of(recurso);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
