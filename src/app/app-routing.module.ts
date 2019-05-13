import { NgModule } from "@angular/core";
import { RouterModule, Routes} from "@angular/router";
import { RegisterComponent } from "./register/register.component";
import { ReservationComponent } from "./reservation/reservation.component";
import { ResultsComponent } from "./results/results.component";
import { LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: '', component: ReservationComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'results', component: ResultsComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
