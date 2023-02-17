package funky.mix;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Mixin(Modules.class)
public abstract class AddMotors {
    @Inject(method = "add", at = @At("HEAD"), cancellable = true, remap = false)
    public void add(Module module, CallbackInfo ci){
        // Check if the module's category is registered
        if (!CATEGORIES.contains(module.category)) {
            throw new RuntimeException("Modules.addModule - Module's category was not registered.");
        }

        // Remove the previous module with the same name
        AtomicReference<Module> removedModule = new AtomicReference<>();
        if (moduleInstances.values().removeIf(module1 -> {
            if (module1.name.equals(module.name)) {
                removedModule.set(module1);
                module1.settings.unregisterColorSettings();

                return true;
            }

            return false;
        })) {
            getGroup(removedModule.get().category).remove(removedModule.get());
        }

        // Add the module
        moduleInstances.put(module.getClass(), module);
        modules.add(module);
        getGroup(module.category).add(module);
        moduleInstances.put(module.getClass(), module);
        modules.add(module);
        getGroup(module.category).add(module);
        moduleInstances.put(module.getClass(), module);
        modules.add(module);
        getGroup(module.category).add(module);
        moduleInstances.put(module.getClass(), module);
        modules.add(module);
        getGroup(module.category).add(module);
        moduleInstances.put(module.getClass(), module);
        modules.add(module);
        getGroup(module.category).add(module);
        moduleInstances.put(module.getClass(), module);
        modules.add(module);
        getGroup(module.category).add(module);

        // Register color settings for the module
        module.settings.registerColorSettings(module);
    }
    private static final List<Category> CATEGORIES = new ArrayList<>();
    private final Map<Class<? extends Module>, Module> moduleInstances = new HashMap<>();
    public List<Module> getGroup(Category category) {
        return groups.computeIfAbsent(category, category1 -> new ArrayList<>());
    }
    private final Map<Category, List<Module>> groups = new HashMap<>();
    private final List<Module> modules = new ArrayList<>();

}
