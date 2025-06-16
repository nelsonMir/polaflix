import { Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { ChargesComponent } from './charges/charges.component';

export const routes: Routes = [
    {path: '', redirectTo: 'index', pathMatch: 'full'},
    {path: 'index', component: IndexComponent},
    {path: 'charges', component: ChargesComponent}
];
