/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Demo2TestModule } from '../../../test.module';
import { PosteComponent } from 'app/entities/poste/poste.component';
import { PosteService } from 'app/entities/poste/poste.service';
import { Poste } from 'app/shared/model/poste.model';

describe('Component Tests', () => {
    describe('Poste Management Component', () => {
        let comp: PosteComponent;
        let fixture: ComponentFixture<PosteComponent>;
        let service: PosteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo2TestModule],
                declarations: [PosteComponent],
                providers: []
            })
                .overrideTemplate(PosteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PosteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PosteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Poste(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.postes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
