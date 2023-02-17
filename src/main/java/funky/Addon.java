package funky;

import com.mojang.logging.LogUtils;
import io.github.racoondog.meteorsharedaddonutils.features.TitleScreenCredits;
import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.systems.config.Config;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;

import java.util.ArrayList;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category FUNKYC = new Category("MotorclientRealrr");

    @Override
    public void onInitialize() {
        LOG.info("Doing Some Funky Things");
        Modules.get().getAll().forEach(module -> module.settings.forEach(group -> group.forEach(Setting::reset)));
        new ArrayList<>(Modules.get().getActive()).forEach(Module::toggle);
        Config.get().customWindowTitle.set(true);
        Config.get().customWindowTitleText.set("Wurst Clint 7.132243 Modified by Harvurst clornt");
        String apple = (" " + Formatting.GRAY + "& " + Formatting.WHITE + "squidoodly");
        apple = apple + apple + apple;
        String finalApple = apple;
        TitleScreenCredits.modifyAddonCredit(MeteorClient.ADDON, credit -> credit.append(Text.literal(finalApple)));
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(FUNKYC);
        for (int i = 1; i < 70; i++) {
            Modules.registerCategory(new Category(i + " fish"));
        }
    }

    @Override
    public String getPackage() {
        return "funky";
    }
}
