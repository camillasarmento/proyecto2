package org.example.projecto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoController(VehiculoRepository vehiculoRepository) {

        this.vehiculoRepository = vehiculoRepository;
    }


    @GetMapping
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            return ResponseEntity.ok(vehiculo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Vehiculo createVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculoDetails) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            Vehiculo vehiculoToUpdate = vehiculo.get();
            vehiculoToUpdate.setTipo(vehiculoDetails.getTipo());
            vehiculoToUpdate.setMarca(vehiculoDetails.getMarca());
            vehiculoToUpdate.setPotencia(vehiculoDetails.getPotencia());
            vehiculoToUpdate.setFecha_compra(vehiculoDetails.getFecha_compra());
            return ResponseEntity.ok(vehiculoRepository.save(vehiculoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            vehiculoRepository.delete(vehiculo.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/average-potencia/{tipo}")
    public ResponseEntity<Double> getAveragePotenciaByTipo(@PathVariable String tipo) {
        Double averagePotencia = vehiculoRepository.getAveragePotenciaByTipo(tipo);
        if (averagePotencia != null) {
            return ResponseEntity.ok(averagePotencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET for obtaining the maximum potencia by vehicle type
    @GetMapping("/maxima-potencia/{tipo}")
    public ResponseEntity<Integer> getMaxPotenciaByTipo(@PathVariable String tipo) {
        Integer maxPotencia = vehiculoRepository.getMaxPotenciaByTipo(tipo);
        if (maxPotencia != null) {
            return ResponseEntity.ok(maxPotencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

