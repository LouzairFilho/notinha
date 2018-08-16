package com.notinha.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.notinha.entidades.Produto;
import com.notinha.service.ProdutoService;



public class lerProduto {
	
	@Autowired
	private ProdutoService produtoService;
	
	
	

	
	public List<Produto> lerprodutos() {
		
	
		
		Produto p = new Produto();
		List<Produto> list = new ArrayList<>();
		
		try {
            //objetos para construir e fazer a leitura do documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            //abre e faz o parser de um documento xml de acordo com o nome passado no parametro
            Document doc = builder.parse("C:\\d\\Desktop\\PRO.xml");
            
            //cria uma lista de pessoas. Busca no documento todas as tag pessoa
            NodeList listaDeProduto = doc.getElementsByTagName("RECORD");
            
            //pego o tamanho da lista de pessoas
            int tamanhoLista = listaDeProduto.getLength();
            
            //varredura na lista de pessoas
            for (int i = 0; i < tamanhoLista; i++) {
                
                //pego cada item (pessoa) como um nó (node)
                Node noProduto = listaDeProduto.item(i);
                
                //verifica se o noPessoa é do tipo element (e não do tipo texto etc)
                if(noProduto.getNodeType() == Node.ELEMENT_NODE){
                    
                    //caso seja um element, converto o no Pessoa em Element pessoa
                    Element elementoProduto = (Element) noProduto;
                    
                          
                    
                    //recupero os nos filhos do elemento pessoa (nome, idade e peso)
                    NodeList listaDeFilhosDaPessoa = elementoProduto.getChildNodes();
                    
                    //pego o tamanho da lista de filhos do elemento pessoa
                    int tamanhoListaFilhos = listaDeFilhosDaPessoa.getLength();
                    int cont = 0;      
                    //varredura na lista de filhos do elemento pessoa
                    for (int j = 0; j < tamanhoListaFilhos; j++) {
                        
                        //crio um no com o cada tag filho dentro do no pessoa (tag nome, idade e peso)
                        Node noFilho = listaDeFilhosDaPessoa.item(j);
                        
                        //verifico se são tipo element
                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){
                            
                            //converto o no filho em element filho
                            Element elementoFilho = (Element) noFilho;
                            
                            //verifico em qual filho estamos pela tag
                            switch(elementoFilho.getTagName()){
                                case "COD":
                                	String srt1 = elementoFilho.getTextContent();
                                    p.setCodigo(srt1.substring(1, srt1.length()-1));
                                    cont++;
                                    break;
                                    
                                case "DESC":
                                	String srt2 = elementoFilho.getTextContent();
                                	p.setDescricao(srt2.substring(1, srt2.length()-1));
                                	cont++;
                                	break;
                                    
                                case "PRECO":
                                	p.setValor(Double.parseDouble(elementoFilho.getTextContent()));
                                	cont++;
                                	break;
                            }
                            
                        }
                        if(cont==3){
                        	
                        	list.add(p);
                        	p=new Produto();
                        	cont=0;
                        }
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(lerProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(lerProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lerProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
		System.out.println(list.size());
		return list;
		
	}
	
	public void salver(List<Produto> lp) {
		for (Produto produto : lp) {
			produtoService.salvar(produto);
		}
	}
	
	
}
