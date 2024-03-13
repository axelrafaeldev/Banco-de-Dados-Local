package DAO;

import DTO.FuncionarioDTO;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel
 */
public class FuncionarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();

    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto) {
        //Esse método tem que receber como parametro um funcionarioDTO
        //esse funcionario vai ser criado no botao de cadastro na VIEW 
        //e vai ser inserido no banco de dados por esse método
        //Caixa de Texto -- objetodto -- objetodto convertido 

        String sql = "insert into funcionarios (nome_funcionario, endereco_funcionario ) values (?,?)";
                
        conn = new Conector().conectaBD();
        //criamos o objeto 'conn' do tipo Connection , esse objeto recebe a classe e o metodo de conexao 
        try {
            pstm = conn.prepareStatement(sql);
            //criamos o objeto pstm que é o tipo prepare statement e passmos pra ele como parâmetro 
            //o objeto 'conn' e executamos o método prepareStatement com parametro sql de comando 
            pstm.setString(1 , objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            //de acordo com a posição 
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO " + erro);
        }

    }
    
    public ArrayList<FuncionarioDTO> PesquisarFuncionario(){
        //AQui estamos definindo um método PEsquisarFuncionario que retorna uma arraylist<FuncionarioDTO>
        String sql = "select * from funcionarios ";
        //criamos uma String que é o comando sql a ser executado
         conn = new Conector().conectaBD();
         //criamos um objeto conn do tipo connection  , que recebe os 
         //parâmetros de conexão da classe Conector, do método conectaBD();
         
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            //Criamos um objeto pstm do tipo PrepareStatement ,esse objeto recebe 
            //como parâmetro o objeto de conexão "conn" e executa a funçao preparestatement 
            //com o parâmetro sql que contem o comando a ser executado
            
            //criramos o objeto rs do tipo ResultSet , que é responsavel pela pesquisa dentro do banco de dados
            //ele recebe o pstm que possui os dados de conexão e execuçao sql , ele usa a função executeQuery();
            //para progredir com a ação , executando os dados pstm e retornando um resultado de busca rs ,
            //que é um tipo ResultSet e vai ser traduzido em um objeto DTO , e listado em uma ArrayList
            
            
            while(rs.next()){
                //loop que diz : enquanto houver proxima linha em rs , lembrando que rs é o resultado
                //da busca no banco de dados , mas ela retorna em formato de ResultSet e deve ser 
                //convertida para objetos DTO
                //lembrando que o metodo next(); faz parte das funçoes do ResultSet, retorna true 
                //quando houver proxima linha e false quando n houver
                
                FuncionarioDTO objfuncionarioDTO = new FuncionarioDTO();
                objfuncionarioDTO.setId_funcionario(rs.getInt("id"));
                objfuncionarioDTO.setNome_funcionario(rs.getString("nome_funcionario"));
                objfuncionarioDTO.setEndereco_funcionario(rs.getString("endereco_funcionario")); 
                //Nessa parte estamos criando um objeto funcionarioDTO , estamos correndo a lista
                //de retorno do "rs" usando o while(rs.next()) , que faz o loop enquanto houver proxima linha , 
                //inicialmente esse cursor fica posicionado antes da primeira linha 
                //ele seta em cada atributo do DTO de acordo com as buscas na linha 
                //correspondente e o termo do banco de dados correspondente ,
                //lembrando que aqui estamos fazendo uma busca nmo nosso retorno "rs" linha a linha
                //e setando de acordo no DTO
                
                lista.add(objfuncionarioDTO);
                
                //A cada loop do while será adicionado o DTO criado na ArrayList "lista"
                
                 
                          
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Pesquisar Funcionário"+erro );
        }
        return lista;
    }
}
