import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { Curso } from '../models/curso';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService extends CommonService<Alumno>{

  protected baseEndPoint = BASE_ENDPOINT + '/alumnos';

  constructor(http: HttpClient) {
    super(http);
  };

  public crearConFoto(alumno: Alumno, archivo: File): Observable<Alumno>{
    const formData = new FormData();

    formData.append('archivo', archivo);
    formData.append('nombre', alumno.nombre);
    formData.append('apellido', alumno.apellido);
    formData.append('email', alumno.email);

    return this.http.post<Alumno>(this.baseEndPoint + '/crear-con-foto', formData);
  }

  public editarConFoto(alumno: Alumno, archivo: File): Observable<Alumno>{
    const formData = new FormData();

    formData.append('archivo', archivo);
    formData.append('nombre', alumno.nombre);
    formData.append('apellido', alumno.apellido);
    formData.append('email', alumno.email);

    return this.http.put<Alumno>(`${this.baseEndPoint}/editar-con-foto/${alumno.id}`, formData);
  }

  public filtrarPorNombre(filtro: string): Observable<Alumno[]> {
    return this.http.get<Alumno[]>(`${this.baseEndPoint}/buscar/${filtro}`);
  }

}


  // COMENTADO POR REEMPLAZO DE PROGRAMACIÓN GENERICA. SE DEJA POR CONSULTAS DE REPASO!!!
  // nos comunicamos a traves de gateway
  // private baseEndPoint = 'http://localhost:8090/api/alumnos';

  // private cabeceras: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  // private http: HttpClient;
  // constructor(http: HttpClient) { this.http=http; }
  // == es lo mismo pero es más limpio
  // constructor(private http: HttpClient) {};

  // public listar(): Observable<Alumno[]> {
    // return this.http.get<Alumno[]>(this.baseEndPoint);
    //return this.http.get(this.baseEndPoint).pipe(
    // map(alumnos => alumnos as Alumno[]) // Hacemos un casteo!
    // map.(alumnos => {
    //   return alumnos as Alumno[];
    // }));
  // }

  //page en relaidad deberia ser de tipo number (xq es un integer) pero como son parámetros del request se envían como string!!
  // lo mismo con size
  // public listarPaginas(page: string, size: string): Observable<any>{
  //   const params = new HttpParams()
  //   .set('page', page)
  //   .set('size', size);
  //   return this.http.get<any>(`${this.baseEndPoint}/paginable`, {params: params});
  // }

  // public ver(id: number): Observable<Alumno> {
  //   return this.http.get<Alumno>(`${this.baseEndPoint}/${id}`);
  // }

  // public crear(alumno: Alumno): Observable<Alumno>{
  //   return this.http.post<Alumno>(this.baseEndPoint, alumno,
  //                                 {headers: this.cabeceras});
  // }

  // public editar(alumno: Alumno): Observable<Alumno>{
  //   return this.http.put<Alumno>(`${this.baseEndPoint}/${alumno.id}`, alumno,
  //                                 {headers: this.cabeceras})
  // }

  // public eliminar(id: number): Observable<void> {
  //   return this.http.delete<void>(`${this.baseEndPoint}/${id}`);
  // }
