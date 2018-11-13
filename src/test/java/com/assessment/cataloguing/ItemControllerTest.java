package com.assessment.cataloguing;

import com.assessment.cataloguing.domain.Item;
import com.assessment.cataloguing.services.ItemService;
import com.assessment.cataloguing.services.MapValidationErrorService;
import com.assessment.cataloguing.web.ItemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private MapValidationErrorService mapValidationErrorService;

    @Test
    public void getItems() throws Exception{

        Item item = new Item();
        item.setItemName("Test");
        item.setItemIdentifier("ITM1");
        item.setDescription("All new item");

        List<Item> allItems = Arrays.asList(item);

        given(itemService.findAllItems()).willReturn(allItems);

        mockMvc.perform(get("/api/item/all")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].itemName").value(item.getItemName()));
    }

    @Test
    public void getItemById() throws Exception {

        Item item = new Item();
        item.setItemName("Test");
        item.setItemIdentifier("ITM1");
        item.setDescription("All new item");

        given(itemService.findItemByIdentifier(item.getItemIdentifier())).willReturn(item);

        mockMvc.perform(get("/api/item/" + item.getItemIdentifier())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("itemIdentifier").value(item.getItemIdentifier()));
    }

}
