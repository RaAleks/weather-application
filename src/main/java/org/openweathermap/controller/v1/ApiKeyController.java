package org.openweathermap.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.openweathermap.service.CacheService;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing API keys in the cache.
 * <p>
 * Supports adding and removing API keys associated with usernames.
 * </p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api-key")
@Tag(name = "API Key Management", description = "Operations to add or remove API keys for users")
public class ApiKeyController {

    private final CacheService cacheService;

    /**
     * Add a new API key associated with a username.
     *
     * @param apiKey   the API key to add
     * @param username the username to associate with the API key
     */
    @Operation(summary = "Add API key",
            description = "Adds a new API key associated with the specified username")
    @PutMapping("/{apiKey}")
    public void add(
            @Parameter(description = "API key to add", required = true)
            @PathVariable String apiKey,
            @Parameter(description = "Username to associate with API key", required = true)
            @RequestParam String username) {

        cacheService.putKey(apiKey, username);
    }

    /**
     * Remove the API key associated with a username.
     *
     * @param username the username whose API key should be removed
     */
    @Operation(summary = "Remove API key",
            description = "Removes the API key associated with the specified username")
    @DeleteMapping
    public void remove(
            @Parameter(description = "Username whose API key will be removed", required = true)
            @RequestParam String username) {

        cacheService.removeKey(username);
    }
}
