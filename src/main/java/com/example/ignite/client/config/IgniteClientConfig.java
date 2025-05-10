package com.example.ignite.client.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class IgniteClientConfig {

    @Bean
    public Ignite igniteClientInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true); //  Thick client mode
        //cfg.setPeerClassLoadingEnabled(true);

        // Optional: Set discovery or client IP
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(List.of("127.0.0.1:47500..47509")); // Match server ports
        discoverySpi.setIpFinder(ipFinder);

        cfg.setDiscoverySpi(discoverySpi);

        return Ignition.start(cfg);
    }
}

