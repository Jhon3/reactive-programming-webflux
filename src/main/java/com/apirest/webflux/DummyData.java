package com.apirest.webflux;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;

import reactor.core.publisher.Flux;

//@Component
public class DummyData  implements CommandLineRunner{

	private final PlaylistRepository playlistRepository;

    DummyData(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        playlistRepository.deleteAll()
                .thenMany(
                        Flux.just("Jazz for Study", "Acoustic Grunge", "R&B", "This is Real State", "This is Marisa Monte", "Time to Cod3")
                        .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                        .flatMap(playlistRepository::save))
                .subscribe(System.out::println);
    }
}