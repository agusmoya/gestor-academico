import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { AlumnosFormComponent } from './components/alumnos/alumnos-form.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { CursoFormComponent } from './components/cursos/curso-form.component';
import { ExamenesComponent } from './components/examenes/examenes.component';
import { ExamenFormComponent } from './components/examenes/examen-form.component';
import { LayoutModule } from './layout/layout.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatPaginatorModule } from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import { MatDialogModule } from '@angular/material/dialog';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatExpansionModule} from '@angular/material/expansion';
import { AsignarAlumnosComponent } from './components/cursos/asignar-alumnos.component';
import { AsignarExamenesComponent } from './components/cursos/asignar-examenes.component';
import { ResponderExamenComponent } from './components/alumnos/responder-examen.component';
import { ResponderExamenModalComponent } from './components/alumnos/responder-examen-modal.component';
import { VerExamenModalComponent } from './components/alumnos/ver-examen-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    AlumnosComponent,
    AlumnosFormComponent,
    CursosComponent,
    CursoFormComponent,
    ExamenesComponent,
    ExamenFormComponent,
    AsignarAlumnosComponent,
    AsignarExamenesComponent,
    ResponderExamenComponent,
    ResponderExamenModalComponent,
    VerExamenModalComponent
  ],
  entryComponents: [
    ResponderExamenModalComponent,
    VerExamenModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatTabsModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
