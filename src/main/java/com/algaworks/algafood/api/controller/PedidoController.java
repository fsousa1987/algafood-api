package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final EmissaoPedidoService emissaoPedido;
    private final PedidoModelAssembler pedidoModelAssembler;
    private final PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    public PedidoController(PedidoRepository pedidoRepository, EmissaoPedidoService emissaoPedido,
                            PedidoModelAssembler pedidoModelAssembler,
                            PedidoResumoModelAssembler pedidoResumoModelAssembler) {
        this.pedidoRepository = pedidoRepository;
        this.emissaoPedido = emissaoPedido;
        this.pedidoModelAssembler = pedidoModelAssembler;
        this.pedidoResumoModelAssembler = pedidoResumoModelAssembler;
    }

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();
        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }
}
