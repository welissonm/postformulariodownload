package br.com.openk.testedownload.testedownload.controller;

import java.nio.file.Files;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.openk.testedownload.testedownload.model.Pessoa;

@Controller
@RequestMapping("/index")
public class DownloadController{

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<byte[]> download(@RequestParam Map<String, String> form){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(form.get("nome"));
        pessoa.setEmail(form.get("email"));
        pessoa.setSexo(Boolean.parseBoolean(form.get("sexo")));
        StringBuilder builder = new StringBuilder();
        builder.append("Titulo: Teste de Download apartir do submite de um fomulário");
        builder.append(String.format("Nome: %s \n", pessoa.getNome()));
        builder.append(String.format("E-mail: %s\n", pessoa.getEmail()));
        builder.append(String.format("E-mail: %s\n", pessoa.getSexo() ? "Marculino": "Feminino"));
        byte[] arquivo = builder.toString().getBytes();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=\"arquivo.txt\"");
        httpHeaders.add("Content-Length", String.valueOf(arquivo.length));
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
        return entity;
    }

}