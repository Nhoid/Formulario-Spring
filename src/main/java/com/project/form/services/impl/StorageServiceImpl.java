package com.project.form.services.impl;

import com.project.form.storage.StorageProperties;
import com.project.form.services.StorageService;
import com.project.form.storage.exception.StorageException;
import com.project.form.storage.exception.StorageFileNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;


// SERVICO DE ARMAZENAMENTO
@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired()
    public StorageServiceImpl(StorageProperties storageProperties) { // RECEBE CONFIGURACOES

        if(storageProperties.getLocation().trim().isEmpty()){
            throw new StorageException("Local para armazenar currículos não foi definido.");
        }

        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    // ARMAZENA ARQUIVO NA PASTA
    @Override
    public String store(MultipartFile file, String name) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Arquivo vázio.");
            }

            // NOME ORIGINAL DO ARQUIVP
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename());

            // NOME ORIGINAL + NOME DO CANDIDADO
            String newFilename = name + "_" + originalFilename;

            // CAMINHO ONDE FOI SALVO
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(newFilename))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // CHECAGEM DE SEGURANCA
                throw new StorageException(
                        "Não pode armazenar no diretório atual.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            // RETORNA LOCAL ONDE FOI ARMAZENADO
            return destinationFile.toString() ;

        } catch (IOException e) {
            throw new StorageException("Falha ao armazenar currículo.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Falha ao carregar currículo.", e);
        }

    }
    // CARREGA ARQUIVO COM BASE NO NOME DELE
    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Não foi possível carregar currículo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Não foi possível ler currículo: " + filename, e);
        }
    }
    // DELETA TODOS ARQRUIVOS
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    // DELETA UM ARQUIVO
    @Override
    public void delete(Path path) {
        path.toFile().delete();
    }

    // METODO DE INICIACAO
    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation); // CRIA PASTA SE NAO EXISTIR
        } catch (IOException e) {
            throw new StorageException("Não foi possível iniciar diretório.", e);
        }
    }
}
