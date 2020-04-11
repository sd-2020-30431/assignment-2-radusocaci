import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GroceryListsComponent} from "./grocery-lists/grocery-lists.component";
import {GroceryListDetailComponent} from "./grocery-list-detail/grocery-list-detail.component";
import {ReportComponent} from "./report/report.component";


const routes: Routes = [
  {path: '', redirectTo: 'grocery-lists', pathMatch: 'full'},
  {path: 'grocery-lists', component: GroceryListsComponent},
  {path: 'grocery-lists/:id', component: GroceryListDetailComponent},
  {path: 'report', component: ReportComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
