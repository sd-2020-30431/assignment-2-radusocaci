import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { GroceryListsComponent } from './grocery-lists/grocery-lists.component';
import {GroceryService} from "./grocery.service";
import { GroceryListComponent } from './grocery-lists/grocery-list/grocery-list.component';
import { GroceryItemComponent } from './grocery-item/grocery-item.component';

@NgModule({
  declarations: [
    AppComponent,
    GroceryListsComponent,
    GroceryListComponent,
    GroceryItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [GroceryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
