package Servicios;

import java.util.List;

import DTOs.CompraDTO;
import Repositorios.CompraRepositorio;

public class CompraServicio {
	
	CompraRepositorio compraRepositorio = new CompraRepositorio();

	public CompraDTO buscarPorId(int id) {
		return compraRepositorio.encontrarPorId(id);
	}
	
	public void agregarCompra(CompraDTO compra) {
		compraRepositorio.guardarCompra(compra);
	}
	
	public List<CompraDTO> obtenerComprasPorUsuario(int idUsuario){
		return compraRepositorio.obtenerComprasPorUsuario(idUsuario);
	}
}
