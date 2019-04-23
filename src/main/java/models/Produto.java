package models;

import java.math.BigDecimal;

public class Produto {
	private int Codigo;
	private int SKU;
	private String CodigoBarras;
	private String CodigoNCM;
	private String Descricao;
	private String Complemento;
	private String Marca;
	private BigDecimal Preco;
	private String Grupo;
	private String Oferta;
	private String DataOferta;
	private double PrecoNormal;
	private String CodigoUnidade;
	private String CodigoDepartamento;
	private String Departamento;
	private boolean Ativo;
	private String CodigoTributario;
	private double Estoque1;	
	private double PrecoLista;
	private double PrecoPDV;
	private double PrecoEtiqueta;
	private String TipoEmbalagem;	
	private int QuantidadeEmbalagem;	

	public Produto() {
		super();
	}
	
	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public int getSKU() {
		return SKU;
	}

	public void setSKU(int sKU) {
		SKU = sKU;
	}

	public String getCodigoBarras() {
		return CodigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		CodigoBarras = codigoBarras;
	}

	public String getCodigoNCM() {
		return CodigoNCM;
	}

	public void setCodigoNCM(String codigoNCM) {
		CodigoNCM = codigoNCM;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public BigDecimal getPreco() {
		return Preco;
	}

	public void setPreco(BigDecimal preco) {
		Preco = preco;
	}

	public String getGrupo() {
		return Grupo;
	}

	public void setGrupo(String grupo) {
		Grupo = grupo;
	}

	public String getOferta() {
		return Oferta;
	}

	public void setOferta(String oferta) {
		Oferta = oferta;
	}

	public String getDataOferta() {
		return DataOferta;
	}

	public void setDataOferta(String dataOferta) {
		DataOferta = dataOferta;
	}

	public double getPrecoNormal() {
		return PrecoNormal;
	}

	public void setPrecoNormal(double precoNormal) {
		PrecoNormal = precoNormal;
	}

	public String getCodigoUnidade() {
		return CodigoUnidade;
	}

	public void setCodigoUnidade(String codigoUnidade) {
		CodigoUnidade = codigoUnidade;
	}

	public String getCodigoDepartamento() {
		return CodigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		CodigoDepartamento = codigoDepartamento;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public boolean isAtivo() {
		return Ativo;
	}

	public void setAtivo(boolean ativo) {
		Ativo = ativo;
	}

	public String getCodigoTributario() {
		return CodigoTributario;
	}

	public void setCodigoTributario(String codigoTributario) {
		CodigoTributario = codigoTributario;
	}

	public double getEstoque1() {
		return Estoque1;
	}

	public void setEstoque1(double estoque1) {
		Estoque1 = estoque1;
	}

	public double getPrecoLista() {
		return PrecoLista;
	}

	public void setPrecoLista(double precoLista) {
		PrecoLista = precoLista;
	}

	public double getPrecoPDV() {
		return PrecoPDV;
	}

	public void setPrecoPDV(double precoPDV) {
		PrecoPDV = precoPDV;
	}

	public double getPrecoEtiqueta() {
		return PrecoEtiqueta;
	}

	public void setPrecoEtiqueta(double precoEtiqueta) {
		PrecoEtiqueta = precoEtiqueta;
	}

	public String getTipoEmbalagem() {
		return TipoEmbalagem;
	}

	public void setTipoEmbalagem(String tipoEmbalagem) {
		TipoEmbalagem = tipoEmbalagem;
	}

	public int getQuantidadeEmbalagem() {
		return QuantidadeEmbalagem;
	}

	public void setQuantidadeEmbalagem(int quantidadeEmbalagem) {
		QuantidadeEmbalagem = quantidadeEmbalagem;
	}

	@Override
	public String toString() {
//		String retorno = "Descrição: " + Descricao;
//		retorno += " - Preço: " + Preco + "\n";
		
		String retorno = Descricao + "," + Preco;
		
		return retorno;
	}
	
}