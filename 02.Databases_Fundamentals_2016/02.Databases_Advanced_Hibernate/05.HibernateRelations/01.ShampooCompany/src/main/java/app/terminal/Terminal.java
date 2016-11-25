package app.terminal;

import app.domain.batches.ProductionBatch;
import app.domain.ingredients.AmmoniumChloride;
import app.domain.ingredients.BasicIngredient;
import app.domain.ingredients.Mint;
import app.domain.ingredients.Nettle;
import app.domain.labels.ClassicLabel;
import app.domain.shampoos.BasicShampoo;
import app.domain.shampoos.FiftyShades;
import app.domain.shampoos.FreshNuke;
import app.services.BasicIngredientService;
import app.services.BasicShampooService;
import app.services.ClassicLabelService;
import app.services.ProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Terminal implements CommandLineRunner {


    @Autowired
    private BasicIngredientService basicIngredientService;

    @Autowired
    private BasicShampooService basicShampooService;

    @Autowired
    private ProductionBatchService productionBatchService;

    @Autowired
    private ClassicLabelService classicLabelService;

    @Override
    public void run(String... strings) throws Exception {

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        ProductionBatch productionBatch = new ProductionBatch(new Date());
        this.productionBatchService.create(productionBatch);

        ClassicLabel classicLabelOne = new ClassicLabel("Aweseom Tittle", "Subtitle");
        this.classicLabelService.create(classicLabelOne);

        ClassicLabel classicLabelTwo = new ClassicLabel("Aweseom 2", "Subtitle2");
        this.classicLabelService.create(classicLabelTwo);

        BasicShampoo basicShampooOne = new FreshNuke(classicLabelOne,productionBatch);
        basicShampooOne.addIngredient(am);
        basicShampooOne.addIngredient(nettle);

        BasicShampoo basicShampooTwo = new FiftyShades(classicLabelTwo,productionBatch);
        basicShampooTwo.addIngredient(am);
        basicShampooTwo.addIngredient(mint);

        am.addShampoo(basicShampooOne);
        nettle.addShampoo(basicShampooOne);

        am.addShampoo(basicShampooTwo);
        mint.addShampoo(basicShampooTwo);

        this.basicIngredientService.create(am);
        this.basicIngredientService.create(mint);
        this.basicIngredientService.create(nettle);

        this.basicShampooService.create(basicShampooOne);
        this.basicShampooService.create(basicShampooTwo);
    }
}
