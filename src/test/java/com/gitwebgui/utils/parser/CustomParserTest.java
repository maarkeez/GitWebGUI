package com.gitwebgui.utils.parser;

import com.gitwebgui.utils.parser.model.ModelWithAnnotations;
import com.gitwebgui.utils.parser.model.ObjectDest;
import com.gitwebgui.utils.parser.model.SubModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomParserTest {

    @Test
    public void customParser(){
        SubModel sm = new SubModel();
        sm.setName("THIS IS sub-model-name: WOOHA! (1)");
        SubModel sm2 = new SubModel();
        sm2.setName("THIS IS sub-model-name: WOOHA! (2)");

        ModelWithAnnotations mwa = new ModelWithAnnotations();
        mwa.setName("THIS IS MY NAME: DMD!");
        mwa.getCustomList().add("Field_1_in_list");
        mwa.getCustomList().add("Field_2_in_list");
        mwa.getCustomList().add("Field_3_in_list");
        mwa.getCustomList().add("Field_4ç_in_list");
        mwa.getSubModelList().add(sm);
        mwa.getSubModelList().add(sm2);

        CustomParser<ModelWithAnnotations> cp = new CustomParser<>();
        ObjectDest od = cp.getObjectDestParser().apply(mwa);

        assertEquals("model_with_annotation",od.getType());
        assertEquals("THIS IS MY NAME: DMD!",od.getFields().get("Model_name"));

    }

}