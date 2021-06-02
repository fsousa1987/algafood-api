package com.algaworks.algafood.infrastructure.service.storage;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

//@Service
public class LocalFotoStorageService implements FotoStorageService {

    private final StorageProperties storageProperties;

//    @Autowired
    public LocalFotoStorageService(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        }
        catch (Exception e) {
            throw new StorageException("Não possível armazenar arquivo.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(arquivoPath);
        }
        catch (Exception e) {
            throw new StorageException("Não possível excluir arquivo.", e);
        }
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            return FotoRecuperada.builder()
                    .inputStream(Files.newInputStream(arquivoPath))
                    .build();
        }
        catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
    }
}
