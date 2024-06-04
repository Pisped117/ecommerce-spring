package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.models.ClienteFrecuente;
import com.andres.app.ecommerce.ecommerce_app.models.Producto;
import com.andres.app.ecommerce.ecommerce_app.services.ClienteFrecuenteService;
import com.andres.app.ecommerce.ecommerce_app.services.DetalleOrdenService;
import com.andres.app.ecommerce.ecommerce_app.services.ProductoService;
import com.andres.app.ecommerce.ecommerce_app.services.impl.ClienteFrecuenteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private ClienteFrecuenteService clienteFrecuenteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DetalleOrdenService detalleOrdenService;


    @GetMapping("/clientes/frecuentes")
    public List<ClienteFrecuente> clientesFrecuentes(){
        return clienteFrecuenteService.listarClienteFrecuente();
    }

    @GetMapping("/productos/activos")
    public List<Producto> productosActivos(){
        return productoService.buscarProductosActivos();
    }

    @GetMapping("/productos/mas/vendidos")
    public List<Map<String, Object>> productosMasVendidos(){
        List<Object[]> productos = detalleOrdenService.listar5ProductosMasVendidos();
        List<Map<String, Object>> body = new ArrayList<>();

        for (Object[] producto : productos){
            Map<String, Object> map = new HashMap<>();
            map.put("idProducto", producto[0]);
            map.put("nombre", producto[1]);
            map.put("precio", producto[2]);
            map.put("stock", producto[3]);
            map.put("totalVendido", producto[4]);
            body.add(map);
        }
        return body;
    }
}

