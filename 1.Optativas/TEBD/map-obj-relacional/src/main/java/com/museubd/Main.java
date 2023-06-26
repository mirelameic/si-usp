package com.museubd;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.museubd.model.bean.Artista;
import com.museubd.model.bean.Colecao;
import com.museubd.model.bean.ObjetosArte;
import com.museubd.model.bean.Pinturas;
import com.museubd.model.bean.Esculturas;
import com.museubd.model.bean.Exposicoes;
import com.museubd.model.bean.Emprestados;
import com.museubd.model.bean.Outros;
import com.museubd.model.bean.Permanentes;
import com.museubd.model.dao.ArtistaDAO;
import com.museubd.model.dao.ColecaoDAO;
import com.museubd.model.dao.ObjetosArteDAO;
import com.museubd.model.dao.PinturasDAO;
import com.museubd.model.dao.EsculturasDAO;
import com.museubd.model.dao.ExposicoesDAO;
import com.museubd.model.dao.EmprestadosDAO;
import com.museubd.model.dao.OutrosDAO;
import com.museubd.model.dao.PermanentesDAO;
import com.museubd.model.dao.ConsultasDAO;

public class Main{
    public static void main(String[] args){
        
        Scanner op = new Scanner(System.in);
        int option;

            System.out.println("\n\n---- Consultas básicas ----");
            System.out.println("Artistas: 1 para cadastrar, 2 para listar, 3 para alterar e 4 para excluir");
            System.out.println("Objetos de arte: 5 para cadastrar, 6 para listar, 7 para alterar e 8 para excluir");
            System.out.println("Coleção: 9 para cadastrar, 10 para listar, 11 para alterar e 12 para excluir");
            System.out.println("Exposição: 13 para cadastrar, 14 para listar, 15 para alterar e 16 para excluir");
            System.out.println("Pintura: 17 para cadastrar, 18 para listar, 19 para alterar e 20 para excluir");
            System.out.println("Escultura: 21 para cadastrar, 22 para listar, 23 para alterar e 24 para excluir");
            System.out.println("Outros: 25 para cadastrar, 26 para listar, 27 para alterar e 28 para excluir");
            System.out.println("Empréstimo: 29 para cadastrar, 30 para listar, 31 para alterar e 32 para excluir");
            System.out.println("Permanente: 33 para cadastrar, 34 para listar, 35 para alterar e 36 para excluir");
            System.out.println("\n---- Consultas especiais ----");
            System.out.println("37 para listar objetos comprados por tipo");
            System.out.println("38 para listar objetos comprados por classe (emprestado ou próprio)");
            System.out.println("39 para listar as coleções com maiores números de empréstimo por mês e por ano");
            System.out.println("40 para listar as compras de objetos de arte por mês e por ano");
            System.out.println("41 para listar a quantidade de objetos emprestados por coleção, por mês e por ano");
            System.out.println("42 para sair");

            
            option = op.nextInt();
            
            switch(option){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    listarArtistas();
                    break;
                case 3:
                    alterarArtista();
                    break;
                case 4:
                    excluirArtista();
                    break;
                case 5:
                    cadastrarObjeto();
                    break;
                case 6:
                    listarObjetos();
                    break;
                case 7:
                    alterarObjeto();
                    break;
                case 8:
                    excluirObjeto();
                    break;
                case 9:
                    cadastrarColecao();
                    break;
                case 10:
                    listarColecoes();
                    break;
                case 11:
                    alterarColecao();
                    break; 
                case 12:
                    excluirColecao();
                    break;   
                case 13:
                    cadastrarExposicao();
                    break;
                case 14:
                    listarExposicoes();
                    break;
                case 15:
                    alterarExposicao();
                    break;
                case 16:
                    excluirExposicao();
                    break;
                case 17:
                    cadastrarPintura();
                    break;
                case 18:
                    listarPinturas();
                    break;
                case 19:
                    alterarPintura();
                    break;
                case 20:
                    excluirPintura();
                    break;
                case 21:
                    cadastrarEscultura();
                    break;
                case 22:
                    listarEsculturas();
                    break;
                case 23:
                    alterarEscultura();
                    break;
                case 24:
                    excluirEscultura();
                    break;
                case 25:
                    cadastrarOutro();
                    break;
                case 26:
                    listarOutros();
                    break;
                case 27:
                    alterarOutro();
                    break;
                case 28:
                    excluirOutro();
                    break;
                case 29:
                    cadastrarEmprestado();
                    break;
                case 30:
                    listarEmprestados();
                    break;
                case 31:
                    alterarEmprestado();
                    break;
                case 32:
                    excluirEmprestado();
                    break;
                case 33:
                    cadastrarPermanente();
                    break;
                case 34:
                    listarPermanentes();
                    break;
                case 35:
                    alterarPermanente();
                    break;
                case 36:
                    excluirPermanente();
                    break;
                case 37:
                    readForTipoAqui();
                    break;
                case 38:
                    readForClasseAqui();
                    break;
                case 39:
                    //
                    break;
                case 40:
                    readForControleAqui();
                    break;
                case 41:
                    readForColecao();
                    break;
                case 42:
                    System.out.println("Fim das consultas!");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

    }

    static void cadastrarArtista(){

        Artista objetoArtista = new Artista();

        System.out.println("Cadastro de artista");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner artista = new Scanner(System.in);
        String nome = artista.next();
        System.out.println("Descrição ");
        String descricao = artista.next();
        System.out.println("Estilo principal ");
        String estiloPrincipal = artista.next();
        System.out.println("Período da arte ");
        String periodoArte = artista.next();
        System.out.println("País de origem ");
        String paisOrigem = artista.next();
        System.out.println("Ano do nascimento ");
        int anoNascimento = artista.nextInt();
        System.out.println("Mês do nascimento ");
        int mesNascimento = artista.nextInt();
        System.out.println("Dia do nascimento ");
        int diaNascimento = artista.nextInt();
        System.out.println("Ano da morte ");
        int anoMorte = artista.nextInt();
        System.out.println("Mês da morte ");
        int mesMorte = artista.nextInt();
        System.out.println("Dia da morte ");
        int diaMorte = artista.nextInt();
        artista.close();

        String traco = "-";
        String dataNasc = anoNascimento + traco + mesNascimento + traco + diaNascimento;
        String dataMort = anoMorte + traco + mesMorte + traco + diaMorte;
        Date dataNascimento = Date.valueOf(dataNasc);
        Date dataMorte = Date.valueOf(dataMort);

        objetoArtista.setNome(nome);
        objetoArtista.setDescricao(descricao);
        objetoArtista.setEstiloPrincipal(estiloPrincipal);
        objetoArtista.setPeriodoArt(periodoArte);
        objetoArtista.setPaisOrig(paisOrigem);
        objetoArtista.setDataNasc(dataNascimento);
        objetoArtista.setDataMorte(dataMorte);

        ArtistaDAO.insert(objetoArtista);
    }

    static void listarArtistas(){
        List<Artista> artistasList = new ArrayList<>();
        artistasList = ArtistaDAO.read();
        for(int i = 0; i<artistasList.size(); i++){
            System.out.println("\nArtistas");
            System.out.println("Nome \t Descrição \t Estilo principal \t Período da arte \t País de origem \t Data de nascimento \t Data de morte");
            System.out.print(artistasList.get(i).getNome() + " \t ");
            System.out.print(artistasList.get(i).getDescricao() + " \t ");
            System.out.print(artistasList.get(i).getEstiloPrincipal() + " \t ");
            System.out.print(artistasList.get(i).getPeriodoArt() + " \t ");
            System.out.print(artistasList.get(i).getPaisOrig() + " \t ");
            System.out.print(artistasList.get(i).getDataNasc() + " \t ");
            System.out.println(artistasList.get(i).getDataMorte() + " \t ");
        }
    }

    static void alterarArtista(){
        Artista objetoArtista = new Artista();

        System.out.println("Alteração de artista");
        System.out.println("Insira os seguintes dados");
        System.out.println("Artista a ser alterado (nome) ");
        Scanner artista = new Scanner(System.in);
        String nome = artista.next();
        System.out.println("Descrição ");
        String descricao = artista.next();
        System.out.println("Estilo principal ");
        String estiloPrincipal = artista.next();
        System.out.println("Período da arte ");
        String periodoArte = artista.next();
        System.out.println("País de origem ");
        String paisOrigem = artista.next();
        System.out.println("Ano do nascimento ");
        int anoNascimento = artista.nextInt();
        System.out.println("Mês do nascimento ");
        int mesNascimento = artista.nextInt();
        System.out.println("Dia do nascimento ");
        int diaNascimento = artista.nextInt();
        System.out.println("Ano da morte ");
        int anoMorte = artista.nextInt();
        System.out.println("Mês da morte ");
        int mesMorte = artista.nextInt();
        System.out.println("Dia da morte ");
        int diaMorte = artista.nextInt();
        artista.close();

        String traco = "-";
        String dataNasc = anoNascimento + traco + mesNascimento + traco + diaNascimento;
        String dataMort = anoMorte + traco + mesMorte + traco + diaMorte;
        Date dataNascimento = Date.valueOf(dataNasc);
        Date dataMorte = Date.valueOf(dataMort);

        objetoArtista.setNome(nome);
        objetoArtista.setDescricao(descricao);
        objetoArtista.setEstiloPrincipal(estiloPrincipal);
        objetoArtista.setPeriodoArt(periodoArte);
        objetoArtista.setPaisOrig(paisOrigem);
        objetoArtista.setDataNasc(dataNascimento);
        objetoArtista.setDataMorte(dataMorte);

        ArtistaDAO.update(objetoArtista);
    }

    static void excluirArtista(){
        Artista objetoArtista = new Artista();

        System.out.println("Exclusão de artista");
        System.out.println("Insira os seguintes dados");
        System.out.println("Artista a ser alterado (nome) ");
        Scanner artista = new Scanner(System.in);
        String nome = artista.next();
        artista.close();

        objetoArtista.setNome(nome);

        ArtistaDAO.delete(objetoArtista);
    }

    static void cadastrarObjeto(){
        
        ObjetosArte objetoObjeto = new ObjetosArte();

        System.out.println("Cadastro de objetos de arte");
        System.out.println("Insira os seguintes dados");
        System.out.println("ID ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        System.out.println("Título ");
        String titulo = objeto.next();
        System.out.println("Nome artista ");
        String nomeArtista = objeto.next();
        System.out.println("Descrição ");
        String descricao = objeto.next();
        System.out.println("Data de criação ");
        String anoCriacao = objeto.next();
        System.out.println("Período da arte ");
        String periodoArte = objeto.next();
        System.out.println("País da cultura ");
        String paisCultura = objeto.next();
        System.out.println("Estilo ");
        String estilo = objeto.next();
        System.out.println("Custo ");
        double custo = objeto.nextDouble();
        System.out.println("Tipo: 1 para pintura, 2 para escultura, 3 para outros ");
        int tipo = objeto.nextInt();

        String tp="", st="";
        switch(tipo){
            case 1:
                tp = "PINTURA"; 
                break;
            case 2:
                tp = "ESCULTURA";
                break;
            case 3:
                tp = "OUTROS";
                break;
        }

        System.out.println("Status: 1 para emprestado, 2 para permanente ");
        int status = objeto.nextInt();
        objeto.close();

        switch(status){
            case 1:
                st = "PERMANENTE";
                break;
            case 2:
                st = "EMPRESTADO";
                break;
        }

        objetoObjeto.setNumID(id);
        objetoObjeto.setTitulo(titulo);
        objetoObjeto.setNomeArtista(nomeArtista);
        objetoObjeto.setDescricao(descricao);
        objetoObjeto.setAnoCriacao(Date.valueOf(anoCriacao));
        objetoObjeto.setPeriodoArt(periodoArte);
        objetoObjeto.setPaisCultura(paisCultura);
        objetoObjeto.setEstilo(estilo);
        objetoObjeto.setTipo(tp);
        objetoObjeto.setStatus(st);
        objetoObjeto.setCusto(custo);

        ObjetosArteDAO.insert(objetoObjeto);
    }

    static void listarObjetos(){

        List<ObjetosArte> objetosList = new ArrayList<>();
        objetosList = ObjetosArteDAO.read();
        for(int i = 0; i<objetosList.size(); i++){
            System.out.println("\n Objetos");
            System.out.println("ID \t Título \t Nome do artista \t Descrição \t Ano de criação \t Período da Arte \t País da cultura \t Estilo \t Tipo \t Status \t Custo");
            System.out.print(objetosList.get(i).getNumID() + " \t ");
            System.out.print(objetosList.get(i).getTitulo() + " \t ");
            System.out.print(objetosList.get(i).getNomeArtista() + " \t ");
            System.out.print(objetosList.get(i).getDescricao() + " \t ");
            System.out.print(objetosList.get(i).getAnoCriacao() + " \t ");
            System.out.print(objetosList.get(i).getPeriodoArt() + " \t ");
            System.out.print(objetosList.get(i).getPaisCultura() + " \t ");
            System.out.print(objetosList.get(i).getEstilo() + " \t ");
            System.out.print(objetosList.get(i).getTipo() + " \t ");
            System.out.print(objetosList.get(i).getStatus() + " \t ");
            System.out.println(objetosList.get(i).getCusto() + " \t ");
        }

    }

    static void alterarObjeto(){

        ObjetosArte objetoObjeto = new ObjetosArte();

        System.out.println("Alteração de objetos de arte");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id a ser alterado ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        System.out.println("Título ");
        String titulo = objeto.next();
        System.out.println("Nome artista ");
        String nomeArtista = objeto.next();
        System.out.println("Descrição ");
        String descricao = objeto.next();
        System.out.println("Ano de criação ");
        String anoCriacao = objeto.next();
        System.out.println("Período da arte ");
        String periodoArte = objeto.next();
        System.out.println("País da cultura ");
        String paisCultura = objeto.next();
        System.out.println("Estilo ");
        String estilo = objeto.next();
        System.out.println("Custo ");
        double custo = objeto.nextDouble();
        System.out.println("Tipo: 1 para pintura, 2 para escultura, 3 para outros ");
        int tipo = objeto.nextInt();

        String tp = "", st="";
        switch(tipo){
            case 1:
                tp = "PINTURA"; 
                break;
            case 2:
                tp = "ESCULTURA";
                break;
            case 3:
                tp = "OUTROS";
                break;
        }

        System.out.println("Status: 1 para emprestado, 2 para permanente ");
        int status = objeto.nextInt();
        objeto.close();

        switch(status){
            case 1:
                st = "PERMANENTE";
                break;
            case 2:
                st = "EMPRESTADO";
                break;
        }

        objetoObjeto.setNumID(id);
        objetoObjeto.setTitulo(titulo);
        objetoObjeto.setNomeArtista(nomeArtista);
        objetoObjeto.setDescricao(descricao);
        objetoObjeto.setAnoCriacao(Date.valueOf(anoCriacao));
        objetoObjeto.setPeriodoArt(periodoArte);
        objetoObjeto.setPaisCultura(paisCultura);
        objetoObjeto.setEstilo(estilo);
        objetoObjeto.setTipo(tp);
        objetoObjeto.setStatus(st);
        objetoObjeto.setCusto(custo);

        ObjetosArteDAO.update(objetoObjeto);
    }

    static void excluirObjeto(){

        ObjetosArte objetoObjeto = new ObjetosArte();

        System.out.println("Cadastro de objetos de arte");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id do objeto ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        objeto.close();

        objetoObjeto.setNumID(id);

        ObjetosArteDAO.delete(objetoObjeto);
    }
    
    static void cadastrarPintura(){
        Pinturas objetoPintura = new Pinturas();

        System.out.println("Cadastro de pintura");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner pintura = new Scanner(System.in);
        int objeto = pintura.nextInt();
        System.out.println("Tipo de tinta ");
        String tipo = pintura.next();
        System.out.println("Suporte ");
        String suporte = pintura.next();
        pintura.close();

        objetoPintura.setNum_obj1(objeto);
        objetoPintura.setTipoTinta(tipo);
        objetoPintura.setSuporte(suporte);

        PinturasDAO.insert(objetoPintura);
    }

    static void listarPinturas(){

        List<Pinturas> pinturasList = new ArrayList<>();
        pinturasList = PinturasDAO.read();
        for(int i = 0; i<pinturasList.size(); i++){
            System.out.println("\n Pinturas");
            System.out.println("Objeto \t Tipo da tinta \t Suporte");
            System.out.print(pinturasList.get(i).getNum_obj1() + " \t ");
            System.out.print(pinturasList.get(i).getTipoTinta() + " \t ");
            System.out.println(pinturasList.get(i).getSuporte() + " \t ");
        }

    }

    static void alterarPintura(){
        Pinturas objetoPintura = new Pinturas();

        System.out.println("Alteração de pintura");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner pintura = new Scanner(System.in);
        int objeto = pintura.nextInt();
        System.out.println("Tipo de tinta ");
        String tipo = pintura.next();
        System.out.println("Suporte ");
        String suporte = pintura.next();
        pintura.close();

        objetoPintura.setNum_obj1(objeto);
        objetoPintura.setTipoTinta(tipo);
        objetoPintura.setSuporte(suporte);

        PinturasDAO.update(objetoPintura);
    }

    static void excluirPintura(){
        Pinturas objetoPintura = new Pinturas();

        System.out.println("Exclusão de pintura");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id do objeto ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        objeto.close();

        objetoPintura.setNum_obj1(id);

        PinturasDAO.delete(objetoPintura);
    }

    static void cadastrarEscultura(){

        Esculturas esculturaObjeto = new Esculturas();

        System.out.println("Cadastro de escultura");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner escultura = new Scanner(System.in);
        int objeto = escultura.nextInt();
        System.out.println("Material ");
        String material = escultura.next();
        System.out.println("Altura ");
        int altura = escultura.nextInt();
        System.out.println("Peso ");
        int peso = escultura.nextInt();
        escultura.close();

        
        esculturaObjeto.setNum_obj2(objeto);
        esculturaObjeto.setMaterial(material);
        esculturaObjeto.setAltura(altura);
        esculturaObjeto.setPeso(peso);

        EsculturasDAO.insert(esculturaObjeto);
    }

    static void listarEsculturas(){

        List<Esculturas> esculturasList = new ArrayList<>();
        esculturasList = EsculturasDAO.read();
        for(int i = 0; i<esculturasList.size(); i++){
            System.out.println("\n Esculturas");
            System.out.println("Objeto \t Material \t Altura \t Peso \t");
            System.out.print(esculturasList.get(i).getNum_obj2() + " \t ");
            System.out.print(esculturasList.get(i).getMaterial() + " \t ");
            System.out.print(esculturasList.get(i).getAltura() + " \t ");
            System.out.println(esculturasList.get(i).getPeso() + " \t ");
        }
    }

    static void alterarEscultura(){
        Esculturas esculturaObjeto = new Esculturas();

        System.out.println("Alteração de escultura");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner escultura = new Scanner(System.in);
        int objeto = escultura.nextInt();
        System.out.println("Material ");
        String material = escultura.next();
        System.out.println("Altura ");
        int altura = escultura.nextInt();
        System.out.println("Peso ");
        int peso = escultura.nextInt();
        escultura.close();

        
        esculturaObjeto.setNum_obj2(objeto);
        esculturaObjeto.setMaterial(material);
        esculturaObjeto.setAltura(altura);
        esculturaObjeto.setPeso(peso);

        EsculturasDAO.update(esculturaObjeto);
    }

    static void excluirEscultura(){
        Esculturas objetoEscultura = new Esculturas();

        System.out.println("Exclusão de escultura");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id do objeto ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        objeto.close();

        objetoEscultura.setNum_obj2(id);

        EsculturasDAO.delete(objetoEscultura);
    }

    static void cadastrarOutro(){
        Outros outroObjeto = new Outros();

        System.out.println("Cadastro de outro tipo de objeto");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner outro = new Scanner(System.in);
        int objeto = outro.nextInt();
        System.out.println("Tipo ");
        String tipo = outro.next();
        outro.close();

        outroObjeto.setNum_obj3(objeto);
        outroObjeto.setTipo(tipo);

        OutrosDAO.insert(outroObjeto);
    }

    static void listarOutros(){
        List<Outros> outrosList = new ArrayList<>();
        outrosList = OutrosDAO.read();
        for(int i = 0; i<outrosList.size(); i++){
            System.out.println("\n Outros");
            System.out.println("Objeto \t Tipo \t");
            System.out.print(outrosList.get(i).getNum_obj3() + " \t ");
            System.out.println(outrosList.get(i).getTipo() + " \t ");
        }

    }

    static void alterarOutro(){
        Outros outroObjeto = new Outros();

        System.out.println("Alteração de outro tipo de objeto");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner outro = new Scanner(System.in);
        int objeto = outro.nextInt();
        System.out.println("Tipo ");
        String tipo = outro.next();
        outro.close();

        outroObjeto.setNum_obj3(objeto);
        outroObjeto.setTipo(tipo);

        OutrosDAO.update(outroObjeto);
    }
    
    static void excluirOutro(){
        Outros outroObjeto = new Outros();

        System.out.println("Cadastro de objetos de arte");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id do objeto ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        objeto.close();

        outroObjeto.setNum_obj3(id);

        OutrosDAO.delete(outroObjeto);
    }

    static void cadastrarEmprestado(){
        Emprestados emprestadoObjeto = new Emprestados();

        System.out.println("Cadastro de empréstimo");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner emprestimo = new Scanner(System.in);
        int objeto = emprestimo.nextInt();
        System.out.println("Nome coleção ");
        String nomeColecao = emprestimo.next();
        System.out.println("Ano do empréstimo ");
        int anoEmp = emprestimo.nextInt();
        System.out.println("Mês do empréstimo ");
        int mesEmp = emprestimo.nextInt();
        System.out.println("Dia do empréstimo ");
        int diaEmp = emprestimo.nextInt();
        System.out.println("Ano da devolução ");
        int anoDev = emprestimo.nextInt();
        System.out.println("Mês da devolução ");
        int mesDev = emprestimo.nextInt();
        System.out.println("Dia da devolução ");
        int diaDev = emprestimo.nextInt();

        String traco = "-";
        String data1 = anoEmp + traco + mesEmp + traco + diaEmp;
        String data2 = anoDev + traco + mesDev + traco + diaDev;
        Date dataEmprestimo = Date.valueOf(data1);
        Date dataDevolucao = Date.valueOf(data2);
        
        emprestimo.close();

        emprestadoObjeto.setNum_obj4(objeto);
        emprestadoObjeto.setNomeColecao(nomeColecao);
        emprestadoObjeto.setDataEmprestimo(dataEmprestimo);
        emprestadoObjeto.setDataDevolucao(dataDevolucao);

        EmprestadosDAO.insert(emprestadoObjeto);
    }

    static void listarEmprestados(){
        List<Emprestados> emprestadosList = new ArrayList<>();
        emprestadosList = EmprestadosDAO.read();
        for(int i = 0; i<emprestadosList.size(); i++){
            System.out.println("\n Emprestados");
            System.out.println("Objeto \t Nome da coleção \t Data de empréstimo \t Data de devolução");
            System.out.print(emprestadosList.get(i).getNum_obj4() + " \t ");
            System.out.print(emprestadosList.get(i).getNomeColecao() + " \t ");
            System.out.print(emprestadosList.get(i).getDataEmprestimo() + " \t ");
            System.out.println(emprestadosList.get(i).getDataDevolucao() + " \t ");
        }
    }

    static void alterarEmprestado(){
        Emprestados emprestadoObjeto = new Emprestados();

        System.out.println("Alteração de empréstimo");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner emprestimo = new Scanner(System.in);
        int objeto = emprestimo.nextInt();
        System.out.println("Nome coleção ");
        String nomeColecao = emprestimo.next();
        System.out.println("Ano do empréstimo ");
        int anoEmp = emprestimo.nextInt();
        System.out.println("Mês do empréstimo ");
        int mesEmp = emprestimo.nextInt();
        System.out.println("Dia do empréstimo ");
        int diaEmp = emprestimo.nextInt();
        System.out.println("Ano da devolução ");
        int anoDev = emprestimo.nextInt();
        System.out.println("Mês da devolução ");
        int mesDev = emprestimo.nextInt();
        System.out.println("Dia da devolução ");
        int diaDev = emprestimo.nextInt();

        String traco = "-";
        String data1 = anoEmp + traco + mesEmp + traco + diaEmp;
        String data2 = anoDev + traco + mesDev + traco + diaDev;
        Date dataEmprestimo = Date.valueOf(data1);
        Date dataDevolucao = Date.valueOf(data2);
        
        emprestimo.close();

        emprestadoObjeto.setNum_obj4(objeto);
        emprestadoObjeto.setNomeColecao(nomeColecao);
        emprestadoObjeto.setDataEmprestimo(dataEmprestimo);
        emprestadoObjeto.setDataDevolucao(dataDevolucao);

        EmprestadosDAO.update(emprestadoObjeto);
    }

    static void excluirEmprestado(){
        Emprestados objetoEmprestado = new Emprestados();

        System.out.println("Exclusão de emprestado");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id do objeto ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        objeto.close();

        objetoEmprestado.setNum_obj4(id);

        EmprestadosDAO.delete(objetoEmprestado);
    }

    static void cadastrarPermanente(){
        Permanentes permanenteObjeto = new Permanentes();

        System.out.println("Cadastro de permanente");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner permanente = new Scanner(System.in);
        int objeto = permanente.nextInt();
        System.out.println("Em exposição? 0 para não e 1 para sim");
        String emExposicao = permanente.next();
        System.out.println("Ano da aquisição ");
        int anoAq = permanente.nextInt();
        System.out.println("Mês da aquisição ");
        int mesAq = permanente.nextInt();
        System.out.println("Dia da aquisição ");
        int diaAq = permanente.nextInt();

        String traco = "-";
        String data1 = anoAq + traco + mesAq + traco + diaAq;
        Date dataAquisicao = Date.valueOf(data1);

        permanente.close();

        permanenteObjeto.setNum_obj5(objeto);
        permanenteObjeto.setEmExposicao(emExposicao);
        permanenteObjeto.setDataAquisicao(dataAquisicao);

        PermanentesDAO.insert(permanenteObjeto);
    }

    static void listarPermanentes(){
        List<Permanentes> permanentesList = new ArrayList<>();
        permanentesList = PermanentesDAO.read();
        for(int i = 0; i<permanentesList.size(); i++){
            System.out.println("\n Permanentes");
            System.out.println("Objeto \t Em exposição \t Data de aquisição");
            System.out.print(permanentesList.get(i).getNum_obj5() + " \t ");
            System.out.print(permanentesList.get(i).getEmExposicao() + " \t ");
            System.out.println(permanentesList.get(i).getDataAquisicao() + " \t ");
        }
    }

    static void alterarPermanente(){
        Permanentes permanenteObjeto = new Permanentes();

        System.out.println("Alteração de permanente");
        System.out.println("Insira os seguintes dados");
        System.out.println("Objeto a que se referencia "); //fk
        Scanner permanente = new Scanner(System.in);
        int objeto = permanente.nextInt();
        System.out.println("Em exposição? 0 para não e 1 para sim");
        String emExposicao = permanente.next();
        System.out.println("Ano da aquisição ");
        int anoAq = permanente.nextInt();
        System.out.println("Mês da aquisição ");
        int mesAq = permanente.nextInt();
        System.out.println("Dia da aquisição ");
        int diaAq = permanente.nextInt();

        String traco = "-";
        String data1 = anoAq + traco + mesAq + traco + diaAq;
        Date dataAquisicao = Date.valueOf(data1);

        permanente.close();

        permanenteObjeto.setNum_obj5(objeto);
        permanenteObjeto.setEmExposicao(emExposicao);
        permanenteObjeto.setDataAquisicao(dataAquisicao);

        PermanentesDAO.update(permanenteObjeto);
    }

    static void excluirPermanente(){
        Permanentes objetoPermanente = new Permanentes();

        System.out.println("Cadastro de objetos de arte");
        System.out.println("Insira os seguintes dados");
        System.out.println("Id do objeto ");
        Scanner objeto = new Scanner(System.in);
        int id = objeto.nextInt();
        objeto.close();

        objetoPermanente.setNum_obj5(id);

        PermanentesDAO.delete(objetoPermanente);
    }

    static void cadastrarExposicao(){
        Exposicoes exposicaoObjeto = new Exposicoes();

        System.out.println("Cadastro de exposição");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner exposicao = new Scanner(System.in);
        String nomeExposicao = exposicao.next();
        System.out.println("Ano de início ");
        int anoIni = exposicao.nextInt();
        System.out.println("Mês de início ");
        int mesIni = exposicao.nextInt();
        System.out.println("Dia de início ");
        int diaIni = exposicao.nextInt();
        System.out.println("Ano final ");
        int anoFin = exposicao.nextInt();
        System.out.println("Mês final ");
        int mesFin = exposicao.nextInt();
        System.out.println("Dia final ");
        int diaFin = exposicao.nextInt();

        String traco = "-";
        String data1 = anoIni + traco + mesIni + traco + traco + diaIni;
        String data2 = anoFin + traco + mesFin + traco + diaFin;
        Date dataInicio = Date.valueOf(data1);
        Date dataFinal = Date.valueOf(data2);

        exposicao.close();

        exposicaoObjeto.setNomeExposicao(nomeExposicao);
        exposicaoObjeto.setDataInicio(dataInicio);
        exposicaoObjeto.setDataFinal(dataFinal);

        ExposicoesDAO.insert(exposicaoObjeto);
    }

    static void listarExposicoes(){
        List<Exposicoes> exposicoesList = new ArrayList<>();
        exposicoesList = ExposicoesDAO.read();
        for(int i = 0; i<exposicoesList.size(); i++){
            System.out.println("\n Exposições");
            System.out.println("Nome \t Data de início \t Data final");
            System.out.print(exposicoesList.get(i).getNomeExposicao() + " \t ");
            System.out.print(exposicoesList.get(i).getDataInicio() + " \t ");
            System.out.print(exposicoesList.get(i).getDataFinal() + " \t ");
        }
    }

    static void alterarExposicao(){
        Exposicoes exposicaoObjeto = new Exposicoes();

        System.out.println("Alteração de exposição");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner exposicao = new Scanner(System.in);
        String nomeExposicao = exposicao.next();
        System.out.println("Ano de início ");
        int anoIni = exposicao.nextInt();
        System.out.println("Mês de início ");
        int mesIni = exposicao.nextInt();
        System.out.println("Dia de início ");
        int diaIni = exposicao.nextInt();
        System.out.println("Ano final ");
        int anoFin = exposicao.nextInt();
        System.out.println("Mês final ");
        int mesFin = exposicao.nextInt();
        System.out.println("Dia final ");
        int diaFin = exposicao.nextInt();

        String traco = "-";
        String data1 = anoIni + traco + mesIni + traco + traco + diaIni;
        String data2 = anoFin + traco + mesFin + traco + diaFin;
        Date dataInicio = Date.valueOf(data1);
        Date dataFinal = Date.valueOf(data2);

        exposicao.close();

        exposicaoObjeto.setNomeExposicao(nomeExposicao);
        exposicaoObjeto.setDataInicio(dataInicio);
        exposicaoObjeto.setDataFinal(dataFinal);

        ExposicoesDAO.update(exposicaoObjeto);
    }

    static void excluirExposicao(){
        Exposicoes exposicaoObjeto = new Exposicoes();

        System.out.println("Exclusão de exposição");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner objeto = new Scanner(System.in);
        String nome = objeto.next();
        objeto.close();

        exposicaoObjeto.setNomeExposicao(nome);

        ExposicoesDAO.delete(exposicaoObjeto);
    }

    static void cadastrarColecao(){
        Colecao colecaoObjeto = new Colecao();

        System.out.println("Cadastro de coleção");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner colecao = new Scanner(System.in);
        String nomeColecao = colecao.next();
        System.out.println("Descrição ");
        String descricao = colecao.next();
        System.out.println("Endereço ");
        String endereco = colecao.next();
        System.out.println("Telefone ");
        String telefone = colecao.next();
        System.out.println("Pessoa de contato ");
        String pessoaContato = colecao.next();
        System.out.println("Tipo da coleção ");
        String tipo = colecao.next();

        colecao.close();

        colecaoObjeto.setNomeColecao(nomeColecao);
        colecaoObjeto.setDescricao(descricao);
        colecaoObjeto.setEndereco(endereco);
        colecaoObjeto.setTelefone(telefone);
        colecaoObjeto.setPessoaContato(pessoaContato);
        colecaoObjeto.setTipoColecao(tipo);

        ColecaoDAO.insert(colecaoObjeto);
    }

    static void listarColecoes(){
        List<Colecao> colecoesList = new ArrayList<>();
        colecoesList = ColecaoDAO.read();
        for(int i = 0; i<colecoesList.size(); i++){
            System.out.println("\n Coleções");
            System.out.println("Nome \t Descrição \t Endereço \t Telefone \t Pessoa para contato \t Tipo");
            System.out.print(colecoesList.get(i).getNomeColecao() + " \t ");
            System.out.print(colecoesList.get(i).getDescricao() + " \t ");
            System.out.print(colecoesList.get(i).getEndereco() + " \t ");
            System.out.print(colecoesList.get(i).getTelefone() + " \t ");
            System.out.print(colecoesList.get(i).getPessoaContato() + " \t ");
            System.out.println(colecoesList.get(i).getTipoColecao() + " \t ");
        }
    }

    static void alterarColecao(){
        Colecao colecaoObjeto = new Colecao();

        System.out.println("Alteração de coleção");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner colecao = new Scanner(System.in);
        String nomeColecao = colecao.next();
        System.out.println("Descrição ");
        String descricao = colecao.next();
        System.out.println("Endereço ");
        String endereco = colecao.next();
        System.out.println("Telefone ");
        String telefone = colecao.next();
        System.out.println("Pessoa de contato ");
        String pessoaContato = colecao.next();
        System.out.println("Tipo da coleção ");
        String tipo = colecao.next();

        colecao.close();

        colecaoObjeto.setNomeColecao(nomeColecao);
        colecaoObjeto.setDescricao(descricao);
        colecaoObjeto.setEndereco(endereco);
        colecaoObjeto.setTelefone(telefone);
        colecaoObjeto.setPessoaContato(pessoaContato);
        colecaoObjeto.setTipoColecao(tipo);

        ColecaoDAO.update(colecaoObjeto);
    }

    static void excluirColecao(){
        Colecao colecaoObjeto = new Colecao();

        System.out.println("Exclusão de coleção");
        System.out.println("Insira os seguintes dados");
        System.out.println("Nome ");
        Scanner objeto = new Scanner(System.in);
        String nome = objeto.next();
        objeto.close();

        colecaoObjeto.setNomeColecao(nome);

        ColecaoDAO.delete(colecaoObjeto);
    }

    static void readForTipoAqui(){

        System.out.print("\n Digite o tipo ");
        Scanner sc = new Scanner(System.in);
        String tipo = sc.next();
        sc.close();

        List<ObjetosArte> objetosList = new ArrayList<>();
        objetosList = ConsultasDAO.readForTipo(tipo);
        for(int i = 0; i<objetosList.size(); i++){
            System.out.println("\n Objetos");
            System.out.println("ID \t Título \t Nome do artista \t Descrição \t Ano de criação \t Período da Arte \t País da cultura \t Estilo \t Tipo \t Status \t Custo");
            System.out.print(objetosList.get(i).getNumID() + " \t ");
            System.out.print(objetosList.get(i).getTitulo() + " \t ");
            System.out.print(objetosList.get(i).getNomeArtista() + " \t ");
            System.out.print(objetosList.get(i).getDescricao() + " \t ");
            System.out.print(objetosList.get(i).getAnoCriacao() + " \t ");
            System.out.print(objetosList.get(i).getPeriodoArt() + " \t ");
            System.out.print(objetosList.get(i).getPaisCultura() + " \t ");
            System.out.print(objetosList.get(i).getEstilo() + " \t ");
            System.out.print(objetosList.get(i).getTipo() + " \t ");
            System.out.print(objetosList.get(i).getStatus() + " \t ");
            System.out.println(objetosList.get(i).getCusto() + " \t ");
        }
    }

    static void readForClasseAqui(){
        System.out.print("\n Digite a classe ");
        Scanner sc = new Scanner(System.in);
        String classe = sc.next();
        sc.close();


        List<ObjetosArte> objetosList = new ArrayList<>();
        objetosList = ConsultasDAO.readForClasse(classe);
        for(int i = 0; i<objetosList.size(); i++){
            System.out.println("\n Objetos");
            System.out.println("ID \t Título \t Nome do artista \t Descrição \t Ano de criação \t Período da Arte \t País da cultura \t Estilo \t Tipo \t Status \t Custo");
            System.out.print(objetosList.get(i).getNumID() + " \t ");
            System.out.print(objetosList.get(i).getTitulo() + " \t ");
            System.out.print(objetosList.get(i).getNomeArtista() + " \t ");
            System.out.print(objetosList.get(i).getDescricao() + " \t ");
            System.out.print(objetosList.get(i).getAnoCriacao() + " \t ");
            System.out.print(objetosList.get(i).getPeriodoArt() + " \t ");
            System.out.print(objetosList.get(i).getPaisCultura() + " \t ");
            System.out.print(objetosList.get(i).getEstilo() + " \t ");
            System.out.print(objetosList.get(i).getTipo() + " \t ");
            System.out.print(objetosList.get(i).getStatus() + " \t ");
            System.out.println(objetosList.get(i).getCusto() + " \t ");
        }
    }

    static void readForControleAqui(){
        System.out.print("\n Digite o mês ");
        Scanner sc = new Scanner(System.in);
        int mes = sc.nextInt();
        System.out.println("Digite o ano ");
        int ano = sc.nextInt();
        sc.close();

        List<ObjetosArte> objetosList = new ArrayList<>();
        objetosList = ConsultasDAO.readControleCompras(mes, ano);
        for(int i = 0; i<objetosList.size(); i++){
            System.out.println("\n Objetos");
            System.out.println("ID \t Título \t Nome do artista \t Descrição \t Ano de criação \t Período da Arte \t País da cultura \t Estilo \t Tipo \t Status \t Custo");
            System.out.print(objetosList.get(i).getNumID() + " \t ");
            System.out.print(objetosList.get(i).getTitulo() + " \t ");
            System.out.print(objetosList.get(i).getNomeArtista() + " \t ");
            System.out.print(objetosList.get(i).getDescricao() + " \t ");
            System.out.print(objetosList.get(i).getAnoCriacao() + " \t ");
            System.out.print(objetosList.get(i).getPeriodoArt() + " \t ");
            System.out.print(objetosList.get(i).getPaisCultura() + " \t ");
            System.out.print(objetosList.get(i).getEstilo() + " \t ");
            System.out.print(objetosList.get(i).getTipo() + " \t ");
            System.out.print(objetosList.get(i).getStatus() + " \t ");
            System.out.println(objetosList.get(i).getCusto() + " \t ");
        }

    }

    static void readForColecao(){
        System.out.print("\n Digite o mês ");
        Scanner sc = new Scanner(System.in);
        int mes = sc.nextInt();
        System.out.println("Digite o ano ");
        int ano = sc.nextInt();
        System.out.println("Digite o nome da coleção ");
        String nome = sc.next();
        sc.close();

        int resposta = ConsultasDAO.readColecao(mes, ano, nome);
        System.out.println("Contagem: " + resposta);
    }
}
