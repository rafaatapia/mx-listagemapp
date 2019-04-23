package telas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import controllers.ProdutoController;
import models.Produto;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.GridData;

public class Listagem {

	protected Shell shell;
	private static Text inpBuscarProdutoCodigo;	
	private static Text inpBuscarProdutoDesc;
	private static ArrayList<Produto> produtosList;
	private Table table2;
	private static Table table;

	public static void main(String[] args) {
		try {
			Listagem window = new Listagem();
			window.open();			
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		
		inpBuscarProdutoCodigo.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void keyPressed(KeyEvent e) {				
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					if(!inpBuscarProdutoCodigo.getText().isEmpty()) {
						buscaProdutoByCodigo(inpBuscarProdutoCodigo.getText());
					} else {
						buscaProduto();
					}
				}
			}
		});
		
		buscaProduto();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setSize(974, 400);		
		shell.setText("Listagem de produtos");
		shell.setLayout(new GridLayout(5, false));
		
		Label lblBuscarProduto = new Label(shell, SWT.NONE);
		lblBuscarProduto.setText("Buscar");
	    
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblDescricao = new Label(shell, SWT.NONE);
		lblDescricao.setAlignment(SWT.CENTER);
		lblDescricao.setText("Descricao:");
		
		inpBuscarProdutoDesc = new Text(shell, SWT.BORDER);
		GridData gd_inpBuscarProdutoDesc = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_inpBuscarProdutoDesc.widthHint = 386;
		inpBuscarProdutoDesc.setLayoutData(gd_inpBuscarProdutoDesc);
		inpBuscarProdutoDesc.setFocus();
		
		inpBuscarProdutoDesc.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					buscaProdutoByDescricao(inpBuscarProdutoDesc.getText());
				}
			}
		});
		
		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCdigo.setAlignment(SWT.CENTER);
		lblCdigo.setText("C\u00F3digo:");
		
		inpBuscarProdutoCodigo = new Text(shell, SWT.BORDER);
		GridData gd_inpBuscarProdutoCodigo = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_inpBuscarProdutoCodigo.widthHint = 355;
		inpBuscarProdutoCodigo.setLayoutData(gd_inpBuscarProdutoCodigo);
	    
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		GridData gd_btnBuscar = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnBuscar.widthHint = 76;
		btnBuscar.setLayoutData(gd_btnBuscar);
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(inpBuscarProdutoCodigo.getText().isEmpty() &&
						inpBuscarProdutoDesc.getText().isEmpty()) {
					buscaProduto();
				} else if(!inpBuscarProdutoCodigo.getText().isEmpty()){
					buscaProdutoByCodigo(inpBuscarProdutoCodigo.getText());
				} else if(!inpBuscarProdutoDesc.getText().isEmpty()) {
					buscaProdutoByDescricao(inpBuscarProdutoDesc.getText());
				}
			}
		});
		btnBuscar.setText("Buscar");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1);
		gd_table_1.widthHint = 932;
		table.setLayoutData(gd_table_1);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		String titles[] = {"Descrição", "Código", "Marca", "Preço", "Codigo de Barras", "Ativo", "Estoque", "Departamento", "SKU", "Grupo", "Oferta", "Data da Oferta", "Código Tributário", "Complemento", "Tipo Embalagem", "Quantidade por Embalagem"};
		
		for (int i = 0; i < titles.length; i++) {
	        TableColumn column = new TableColumn(table, SWT.NULL);
	        column.setText(titles[i]);
	        column.setWidth(200);	        
	        column.setResizable(true);
	        column.setMoveable(true);
	    }

	}
	
	private static void buscaProduto() {
		ProdutoController produtoController = new ProdutoController();
		produtosList = produtoController.buscaProdutos();
		
		for(Produto p : produtosList) {
			montaCorpoTabela(p);
		}
	}
	
	private static void buscaProdutoByCodigo(String codigo) {
		ArrayList<Produto> encontrados = new ArrayList<Produto>();
		for(Produto p : produtosList) {
			if(String.valueOf(p.getCodigo()).toLowerCase().contains(codigo.toLowerCase())){
				encontrados.add(p);
			}
		}
		table.removeAll();
		for(Produto produto : encontrados) {
			montaCorpoTabela(produto);
		}
	}
	
	private static void buscaProdutoByDescricao(String descricao) {
		ArrayList<Produto> encontrados = new ArrayList<Produto>();
		for(Produto p : produtosList) {
			if(p.getDescricao().toLowerCase().contains(descricao.toLowerCase())){
				encontrados.add(p);
			}
		}
		table.removeAll();
		for(Produto produto : encontrados) {
			montaCorpoTabela(produto);
		}
	}
	
	//String titles[] = {"Descri��o", "C�digo", "Marca", "Pre�o", "Codigo de Barras", "Ativo", "Estoque", "Departamento", "SKU", "Grupo", "Oferta", "Data da Oferta", "C�digo Tribut�rio", "Complemento", "Tipo Embalagem", "Quantidade por Embalagem"};
	
	private static void montaCorpoTabela(Produto p) {
		TableItem tableItem= new TableItem(table, SWT.NONE);
		tableItem.setText(0, p.getDescricao());
		tableItem.setText(1, String.valueOf(p.getCodigo()));
		tableItem.setText(2, p.getMarca());
		tableItem.setText(3, "R$"+String.valueOf(p.getPreco().setScale(2,BigDecimal.ROUND_DOWN)));
		tableItem.setText(4, p.getCodigoBarras());
		tableItem.setText(5, (p.isAtivo() ? "Ativo" : "Inativo"));
		tableItem.setText(6, String.valueOf(p.getEstoque1()));
		tableItem.setText(7, p.getDepartamento());
		tableItem.setText(8, String.valueOf(p.getSKU()));
		tableItem.setText(9, p.getGrupo());
		tableItem.setText(10, p.getOferta());
		tableItem.setText(11, p.getDataOferta());
		tableItem.setText(12, p.getCodigoTributario());
		tableItem.setText(13, p.getComplemento());
		tableItem.setText(14, p.getTipoEmbalagem());
		tableItem.setText(15, String.valueOf(p.getQuantidadeEmbalagem()));
	}
}
