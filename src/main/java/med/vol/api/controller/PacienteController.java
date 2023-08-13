package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhadosPaciente> detalhar(@PathVariable Long id)
    {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhadosPaciente(paciente));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhadosPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhadosPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable page){
        var paginacao = repository.findAllByAtivoTrue(page).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhadosPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhadosPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

}
