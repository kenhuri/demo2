/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Demo2TestModule } from '../../../test.module';
import { BureauDeleteDialogComponent } from 'app/entities/bureau/bureau-delete-dialog.component';
import { BureauService } from 'app/entities/bureau/bureau.service';

describe('Component Tests', () => {
    describe('Bureau Management Delete Component', () => {
        let comp: BureauDeleteDialogComponent;
        let fixture: ComponentFixture<BureauDeleteDialogComponent>;
        let service: BureauService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo2TestModule],
                declarations: [BureauDeleteDialogComponent]
            })
                .overrideTemplate(BureauDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BureauDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BureauService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
