import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnosFormComponent } from './components/alumnos/alumnos-form.component';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { ResponderExamenComponent } from './components/alumnos/responder-examen.component';
import { AsignarAlumnosComponent } from './components/cursos/asignar-alumnos.component';
import { AsignarExamenesComponent } from './components/cursos/asignar-examenes.component';
import { CursoFormComponent } from './components/cursos/curso-form.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { ExamenFormComponent } from './components/examenes/examen-form.component';
import { ExamenesComponent } from './components/examenes/examenes.component';

// AQUI REGISTRAMOS LAS RUTAS A LOS COMPONENETES QUE HEMOS CREADO.
// EL CONTENIDO DE ESTAS RUTAS SE INSERTARAN DONDE FIGURA EL SELECTOR <router-outlet>
const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo:'cursos'}, //por defecto, pathMatch: prefix (?)
  {path:'alumnos', component:AlumnosComponent},
  {path:'alumnos/form', component:AlumnosFormComponent},
  {path:'alumnos/form/:id', component:AlumnosFormComponent},
  {path:'alumnos/responder-examen/:id', component:ResponderExamenComponent},
  {path:'cursos', component:CursosComponent},
  {path:'cursos/form', component:CursoFormComponent},
  {path:'cursos/form/:id', component:CursoFormComponent},
  {path:'cursos/asignar-alumnos/:id', component:AsignarAlumnosComponent},
  {path:'cursos/asignar-examenes/:id', component:AsignarExamenesComponent},
  {path:'examenes', component:ExamenesComponent},
  {path:'examenes/form', component:ExamenFormComponent},
  {path:'examenes/form/:id', component:ExamenFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }