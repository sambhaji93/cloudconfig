import org.springframework.vault.core.VaultTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vault")
public class VaultController {

    private final VaultTemplate vaultTemplate;

    public VaultController(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @PostMapping("/secrets")
    public ResponseEntity<String> addSecret(@RequestBody Map<String, String> secret) {
        vaultTemplate.write("secret/myapp", secret);
        return ResponseEntity.ok("Secret added successfully");
    }
}
